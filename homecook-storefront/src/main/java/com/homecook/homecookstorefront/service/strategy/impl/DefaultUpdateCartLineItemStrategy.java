package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.strategy.UpdateCartLineItemStrategy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNull;

@Component(value = "updateCartLineItemStrategy")
public class DefaultUpdateCartLineItemStrategy extends AbstractCartStrategy
        implements UpdateCartLineItemStrategy
{
    @Transactional
    public CartEntity updateQuantityForCartLineItem(CommerceCartParameter parameter)
    {
        final CartEntity cart = parameter.getCart();
        final SKUProduct skuProduct = parameter.getSkuProduct();
        final int newQuantity = parameter.getQuantity();

        validateParameterNotNull(cart, "cart model cannot be null");
        validateParameterNotNull(skuProduct, "skuProduct cannot be null");

        final CartLineItemEntity lineItemToUpdate
                = getCartService().getCartLineItemForSKUProduct(cart, skuProduct);
        validateLineItemBeforeModification(newQuantity, lineItemToUpdate);

        final Integer maxOrderQuantity = lineItemToUpdate.getProduct().getMaxOrderQuantity();
        // Work out how many we want to add (could be negative if we are removing items)
        final int quantityToAdd = newQuantity - lineItemToUpdate.getQuantity();

        if (isStockLevelSufficient(cart, parameter.getSkuProduct(), quantityToAdd))
        {
            // So now work out what the maximum allowed to be added is (note that
            // this may be negative!)
            final int actualAllowedQuantityChange = getAllowedCartQtyAdjustmentForProduct(cart, parameter.getSkuProduct(),
                    quantityToAdd);
            // Now do the actual cartModification
            return modifyLineItem(cart, lineItemToUpdate, actualAllowedQuantityChange, newQuantity, maxOrderQuantity);
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.UPDATE_CART_INVALID, "Insufficient stock");
        }
    }

    protected CartEntity modifyLineItem(final CartEntity cartModel, final CartLineItemEntity lineItemToUpdate,
                                        final int actualAllowedQuantityChange, final int newQuantity, final Integer maxOrderQuantity)
    {
        // Now work out how many that leaves us with on this entry
        final int lineItemNewQuantity = lineItemToUpdate.getQuantity() + actualAllowedQuantityChange;

        if (lineItemNewQuantity <= 0)
        {
            // The allowed new line item quantity is zero or negative
            // just remove the line item
            cartModel.getLineItems().remove(lineItemToUpdate);
            getModelService().save(cartModel);

            final CommerceCartParameter parameter = new CommerceCartParameter();
            parameter.setCart(cartModel);
            getCartCalculationStrategy().calculateCart(parameter);

            return parameter.getCart();
        }
        else
        {
            if (isMaxOrderQuantitySet(maxOrderQuantity) && newQuantity > maxOrderQuantity && lineItemNewQuantity == maxOrderQuantity && actualAllowedQuantityChange == 0)
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Could not update the requested number of items to cart due to max order quantity exceeded");
            }

            // Adjust the lineItem quantity to the new value
            lineItemToUpdate.setQuantity(lineItemNewQuantity);
            getModelService().save(lineItemToUpdate);
            getModelService().refresh(cartModel);

            final CommerceCartParameter parameter = new CommerceCartParameter();
            parameter.setCart(cartModel);
            getCartCalculationStrategy().calculateCart(parameter);

            return parameter.getCart();
        }
    }

    protected void validateLineItemBeforeModification(final long newQuantity, final CartLineItemEntity lineItemToUpdate)
    {
        if (newQuantity < 0)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.UPDATE_CART_INVALID, "New quantity must not be less than zero");
        }

        if (lineItemToUpdate == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.UPDATE_CART_INVALID, "lineItem not found in exists cart");
        }
    }

    protected boolean isStockLevelSufficient(final CartEntity cartEntity, final SKUProduct skuProduct,
                                             final long quantityToAdd)
    {
        final long cartLevel = checkCartLevel(cartEntity, skuProduct);
        final long stockLevel = getAvailableSellingStockLevel(skuProduct);

        final long newTotalQuantity = cartLevel + quantityToAdd;
        return newTotalQuantity <= stockLevel;
    }
}
