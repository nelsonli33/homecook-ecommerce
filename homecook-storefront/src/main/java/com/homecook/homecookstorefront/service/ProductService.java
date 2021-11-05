package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookstorefront.dto.SKUProduct;

public interface ProductService
{
    ProductEntity getProductForId(Long productId);

    ProductVariantEntity getVariantForProduct(ProductEntity product, Long variantId);

    SKUProduct getSKUProduct(ProductEntity product, ProductVariantEntity productVariant);
}
