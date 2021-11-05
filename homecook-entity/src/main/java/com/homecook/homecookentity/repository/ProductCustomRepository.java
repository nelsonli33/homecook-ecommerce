package com.homecook.homecookentity.repository;

public interface ProductCustomRepository
{
    long reserveStockByProductVariant(Long productVariantId, Integer quantity);

    long releaseStockByProductVariant(Long productVariantId, Integer quantity);

    long reduceStockByProductVariant(Long productVariantId, Integer quantity);
}
