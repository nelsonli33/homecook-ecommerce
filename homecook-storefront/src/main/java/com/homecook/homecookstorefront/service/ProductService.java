package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.ProductEntity;

public interface ProductService
{
    ProductEntity getProductForId(Long productId);
}
