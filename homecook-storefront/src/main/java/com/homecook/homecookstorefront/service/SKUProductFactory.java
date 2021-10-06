package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookstorefront.dto.SKUProduct;

public interface SKUProductFactory
{
    SKUProduct createSKUProduct(ProductEntity product, ProductVariantEntity variant);
}
