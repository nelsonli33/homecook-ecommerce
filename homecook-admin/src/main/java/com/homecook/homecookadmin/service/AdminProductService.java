package com.homecook.homecookadmin.service;

import com.homecook.homecookentity.entity.ProductVariantEntity;

public interface AdminProductService
{
    ProductVariantEntity getProductVariantForId(Long variantId);
}
