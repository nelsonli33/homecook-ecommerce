package com.homecook.homecookstorefront.service.strategy.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.StockService;
import com.homecook.homecookstorefront.service.strategy.CartCalculationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCartStrategy
{
    protected static final int DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY = 9999;

    protected int forceInStockMaxQuantity = DEFAULT_FORCE_IN_STOCK_MAX_QUANTITY;

    private CartService cartService;
    private StockService stockService;
    private ModelService modelService;
    private CartCalculationStrategy cartCalculationStrategy;

    protected int getAllowedCartQtyAdjustmentForProduct(final CartEntity cartEntity, final SKUProduct skuProduct,
                                                        final int quantityToAdd)
    {
        final int cartLevel = checkCartLevel(cartEntity, skuProduct);
        final int stockLevel = getAvailableSellingStockLevel(skuProduct);

        // How many will we have in our cart if we add quantity
        final int newTotalQuantity = cartLevel + quantityToAdd;

        // Now limit that to the total available in stock
        final int newTotalQuantityAfterStockLimit = Math.min(newTotalQuantity, stockLevel);

        // So now work out what the maximum allowed to be added
        final Integer maxOrderQuantity = skuProduct.getProduct().getMaxOrderQuantity();

        if (isMaxOrderQuantitySet(maxOrderQuantity))
        {
            final int newTotalQuantityAfterProductMaxOrder = Math
                    .min(newTotalQuantityAfterStockLimit, maxOrderQuantity);

            return newTotalQuantityAfterProductMaxOrder - cartLevel;
        }
        return newTotalQuantityAfterStockLimit - cartLevel;
    }

    protected int checkCartLevel(final CartEntity cartEntity, final SKUProduct skuProduct)
    {
        int cartLevel = 0;

        CartLineItemEntity lineItem = getCartService().getCartLineItemForSKUProduct(cartEntity, skuProduct);

        if (lineItem != null)
        {
            cartLevel += lineItem.getQuantity();
        }

        return cartLevel;
    }


    protected int getAvailableSellingStockLevel(final SKUProduct skuProduct)
    {
        final Integer availableSellingStockLevel;

        availableSellingStockLevel = getStockService().getAvailableToSellQuantity(skuProduct.getVariant());

        if (availableSellingStockLevel == null)
        {
            return getForceInStockMaxQuantity();
        }
        else
        {
            return availableSellingStockLevel;
        }
    }

    protected boolean isMaxOrderQuantitySet(final Integer maxOrderQuantity)
    {
        return maxOrderQuantity != null;
    }

    public int getForceInStockMaxQuantity()
    {
        return forceInStockMaxQuantity;
    }

    public CartService getCartService()
    {
        return cartService;
    }

    @Autowired
    public void setCartService(CartService cartService)
    {
        this.cartService = cartService;
    }

    public StockService getStockService()
    {
        return stockService;
    }

    @Autowired
    public void setStockService(StockService stockService)
    {
        this.stockService = stockService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    @Autowired
    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public CartCalculationStrategy getCartCalculationStrategy()
    {
        return cartCalculationStrategy;
    }

    @Autowired
    public void setCartCalculationStrategy(CartCalculationStrategy cartCalculationStrategy)
    {
        this.cartCalculationStrategy = cartCalculationStrategy;
    }
}
