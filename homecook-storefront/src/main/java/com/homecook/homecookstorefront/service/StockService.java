package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.ProductVariantEntity;

public interface StockService
{
    boolean reserveStock(ProductVariantEntity productVariant, int quantity);

    boolean reduceStock(ProductVariantEntity productVariant, int quantity);

    Integer getAvailableToSellQuantity(ProductVariantEntity productVariant);
}
