package com.homecook.homecookadmin.service;

import com.homecook.homecookadmin.dto.ProductSearchCriteria;
import com.homecook.homecookentity.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminProductService
{
    Page<ProductEntity> getProducts(ProductSearchCriteria searchCriteria);

    ProductEntity getProductForId(Long productId);

    List<ProductEntity> getAvailableProductsForIds(List<Long> productIds);

    ProductSpecAttributeEntity getProductSpecAttribute(Long attributeId, ProductEntity productEntity);

    ProductAttributeValueEntity getProductAttributeValue(Long attributeValueId, ProductSpecAttributeEntity productSpecAttributeEntity);

    ProductVariantEntity getProductVariantForId(Long variantId);

    void deleteAllProductSpecsAndVariantsForProduct(ProductEntity productEntity);

    VoucherEntity getActiveVoucherForProduct(ProductEntity productEntity);
}
