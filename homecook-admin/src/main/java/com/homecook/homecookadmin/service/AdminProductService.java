package com.homecook.homecookadmin.service;

import com.homecook.homecookadmin.dto.ProductSearchCriteria;
import com.homecook.homecookentity.entity.ProductAttributeValueEntity;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductSpecAttributeEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import org.springframework.data.domain.Page;

public interface AdminProductService
{
    Page<ProductEntity> getProducts(ProductSearchCriteria searchCriteria);

    ProductEntity getProductForId(Long productId);

    ProductSpecAttributeEntity getProductSpecAttribute(Long attributeId, ProductEntity productEntity);

    ProductAttributeValueEntity getProductAttributeValue(Long attributeValueId, ProductSpecAttributeEntity productSpecAttributeEntity);

    ProductVariantEntity getProductVariantForId(Long variantId);




    void deleteAllProductSpecsAndVariantsForProduct(ProductEntity productEntity);
}
