package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminProductService;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookentity.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "adminProductService")
public class DefaultAdminProductService implements AdminProductService
{
    private ProductVariantRepository productVariantRepository;

    @Autowired
    public DefaultAdminProductService(ProductVariantRepository productVariantRepository)
    {
        this.productVariantRepository = productVariantRepository;
    }

    @Override
    public ProductVariantEntity getProductVariantForId(Long variantId)
    {
        final Optional<ProductVariantEntity> productVariantEntity = productVariantRepository.findById(variantId);
        if (!productVariantEntity.isPresent()) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Variant with id "+variantId+" requested for the object does not exists in the system");
        }
        return productVariantEntity.get();
    }
}
