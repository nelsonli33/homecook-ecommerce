package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.strategy.AddToCartStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNull;

@Component(value = "addToCartStrategy")
public class DefaultAddToCartStrategy extends AbstractCartStrategy implements AddToCartStrategy
{
    @Transactional
    public CartEntity addToCart(CommerceCartParameter parameter)
    {
        doAddToCart(parameter);
        getCartCalculationStrategy().calculateCart(parameter);
        return parameter.getCart();
    }

    protected void doAddToCart(CommerceCartParameter parameter)
    {
        final CartEntity cart = parameter.getCart();
        final SKUProduct skuProduct = parameter.getSkuProduct();
        final int quantityToAdd = parameter.getQuantity();

        validateAddToCart(parameter);

        // So now work out what the maximum allowed to be added is (note that this may be negative!)
        final int actualAllowedQuantityChange = getAllowedCartQtyAdjustmentForProduct(cart, skuProduct, quantityToAdd);
        final int cartLevel = checkCartLevel(cart, skuProduct);
        final int cartLevelAfterQuantityChange = actualAllowedQuantityChange + cartLevel;
        final Integer maxOrderQuantity = skuProduct.getProduct().getMaxOrderQuantity();

        validateAllowedQuantityChange(actualAllowedQuantityChange, maxOrderQuantity, quantityToAdd, cartLevelAfterQuantityChange);

        if (actualAllowedQuantityChange > 0)
        {
            final CartLineItemEntity cartLineItemEntity = getCartService().addLineItem(cart, skuProduct, actualAllowedQuantityChange);
            getModelService().save(cartLineItemEntity);
        }
    }


    protected void validateAddToCart(final CommerceCartParameter parameter)
    {
        final CartEntity cartModel = parameter.getCart();
        final SKUProduct skuProduct = parameter.getSkuProduct();

        validateParameterNotNull(cartModel, "CartEntity cannot be null");
        validateParameterNotNull(skuProduct, "SKUProduct cannot be null");
        validateParameterNotNull(skuProduct.getProduct(), "ProductEntity cannot be null");

        if (!CollectionUtils.isEmpty(skuProduct.getProduct().getVariants()) && skuProduct.getVariant() == null)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Need to choose a variant");
        }

        if (parameter.getQuantity() < 1)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Quantity must not be less than one");
        }
    }

    protected void validateAllowedQuantityChange(final int actualAllowedQuantityChange, final Integer maxOrderQuantity,
                                                 final int quantityToAdd, final int cartLevelAfterQuantityChange)
    {
        if (actualAllowedQuantityChange > 0)
        {
            if (isMaxOrderQuantitySet(maxOrderQuantity) && (actualAllowedQuantityChange < quantityToAdd)
                    && (cartLevelAfterQuantityChange == maxOrderQuantity))
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Could not add the requested number of items to cart due to max order quantity exceeded");
            }
        }
        else
        {
            if (isMaxOrderQuantitySet(maxOrderQuantity) && (cartLevelAfterQuantityChange == maxOrderQuantity))
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Could not add the requested number of items to cart due to max order quantity exceeded");
            }
            else
            {
                throw new StorefrontServerRuntimeException(InternalErrorCode.ADD_TO_CART_INVALID, "Could not add the requested number of items to cart due to no stock");
            }
        }
    }
}
