package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;

public interface ProductService
{
    ProductEntity getProductForId(Long productId);

    ProductVariantEntity getVariantForProduct(ProductEntity product, Long variantId);
}
