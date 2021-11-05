package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.CartError;
import com.homecook.homecookstorefront.error.CartLineItemError;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.error.type.CartLineItemInvalidType;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.ProductService;
import com.homecook.homecookstorefront.service.StockService;
import com.homecook.homecookstorefront.service.strategy.CartValidationStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "cartValidationStrategy")
public class DefaultCartValidationStrategy implements CartValidationStrategy
{
    private ProductService productService;
    private CartService cartService;
    private StockService stockService;
    private ModelService modelService;

    @Autowired
    public DefaultCartValidationStrategy(ProductService productService, CartService cartService, StockService stockService, ModelService modelService)
    {
        this.productService = productService;
        this.cartService = cartService;
        this.stockService = stockService;
        this.modelService = modelService;
    }

    @Override
    public void validateCart(CommerceCartParameter parameter)
    {
        final CartEntity cartEntity = parameter.getCart();
        validateParameterNotNullStandardMessage("cartEntity", cartEntity);

        List<CartLineItemError> cartLineItemErrors = new ArrayList<>();

        if (cartEntity != null && cartEntity.getLineItems() != null && !cartEntity.getLineItems().isEmpty())
        {
            for (final CartLineItemEntity cartLineItemEntity : cartEntity.getLineItems())
            {
                final CartLineItemError error = validateCartLineItem(cartLineItemEntity);
                if (error != null)
                {
                    cartLineItemErrors.add(error);
                }
            }
        }

        if (CollectionUtils.isNotEmpty(cartLineItemErrors))
        {
            CartError cartError = new CartError();
            cartError.setProblematicItems(cartLineItemErrors);
            throw new StorefrontServerRuntimeException(InternalErrorCode.CART_CHECKOUT_ERROR, "", cartError);
        }

    }

    private CartLineItemError validateCartLineItem(CartLineItemEntity cartLineItemEntity)
    {
        // First verify that the product exists
        try
        {
            getProductService().getProductForId(cartLineItemEntity.getProductId());
        }
        catch (StorefrontServerRuntimeException ex)
        {
            CartLineItemError error = new CartLineItemError();
            error.setLineItemId(cartLineItemEntity.getId());
            error.setProductId(cartLineItemEntity.getProductId());
            error.setVariantId(cartLineItemEntity.getVariantId());
            error.setInvalidType(CartLineItemInvalidType.PRDOUCT_UNAVAILABLE);
            error.setErrorMessage("The product is unpublished");
            return error;
        }

        // Second verify if stock sufficient

        final Integer stockLevel = getStockService().getAvailableToSellQuantity(cartLineItemEntity.getVariant());

        final int cartLineItemLevel = cartLineItemEntity.getQuantity();

        final int newCartLineItemLevel;

        if (stockLevel != null)
        {
            // if stock is available. get either requested quantity if its lower than available stock or maximum stock.
            newCartLineItemLevel = Math.min(stockLevel, cartLineItemLevel);
        }
        else
        {
            // if stock is not available. only allow quantity that was already in cart.
            newCartLineItemLevel = cartLineItemLevel;
        }


        if (stockLevel != null && stockLevel <= 0)
        {
            CartLineItemError error = new CartLineItemError();
            error.setLineItemId(cartLineItemEntity.getId());
            error.setProductId(cartLineItemEntity.getProductId());
            error.setVariantId(cartLineItemEntity.getVariantId());
            error.setInvalidType(CartLineItemInvalidType.PRODUCT_NO_STOCK);
            error.setErrorMessage("No stock");
            return error;
        }
        else if (cartLineItemLevel != newCartLineItemLevel)
        {
            CartLineItemError error = new CartLineItemError();
            error.setLineItemId(cartLineItemEntity.getId());
            error.setProductId(cartLineItemEntity.getProductId());
            error.setVariantId(cartLineItemEntity.getVariantId());
            error.setInvalidType(CartLineItemInvalidType.PRODUCT_LOW_STOCK);
            error.setErrorMessage("For each checkout, this product can be purchased " + newCartLineItemLevel + " quantity (including all specifications), please adjust the quantity and check out again.");
            return error;
        }


        // Third verify if product info has changed
        final SKUProduct skuProduct
                = getProductService().getSKUProduct(cartLineItemEntity.getProduct(), cartLineItemEntity.getVariant());
        if (!skuProduct.getKey().equals(cartLineItemEntity.getItemKey()))
        {
            CartLineItemError error = new CartLineItemError();
            error.setLineItemId(cartLineItemEntity.getId());
            error.setProductId(cartLineItemEntity.getProductId());
            error.setVariantId(cartLineItemEntity.getVariantId());
            error.setInvalidType(CartLineItemInvalidType.PRODUCT_INFO_HAS_CHANGED);
            error.setErrorMessage("The product information in the cart has been modified");
            return error;
        }

        return null;
    }


    public ProductService getProductService()
    {
        return productService;
    }

    public void setProductService(ProductService productService)
    {
        this.productService = productService;
    }

    public StockService getStockService()
    {
        return stockService;
    }

    public void setStockService(StockService stockService)
    {
        this.stockService = stockService;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
