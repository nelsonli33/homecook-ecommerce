package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.dto.ProductSearchCriteria;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminProductService;
import com.homecook.homecookadmin.service.AdminVoucherService;
import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.repository.ProductRepository;
import com.homecook.homecookentity.repository.ProductSpecAttributeRepository;
import com.homecook.homecookentity.repository.ProductVariantRepository;
import com.homecook.homecookentity.type.ProductStatusType;
import com.homecook.homecookentity.type.VoucherStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "adminProductService")
public class DefaultAdminProductService extends AbstractBaseService implements AdminProductService
{
    private ProductVariantRepository productVariantRepository;
    private ProductRepository productRepository;
    private ProductSpecAttributeRepository productSpecAttributeRepository;
    private AdminVoucherService adminVoucherService;

    @Autowired
    public DefaultAdminProductService(ProductVariantRepository productVariantRepository, ProductRepository productRepository, ProductSpecAttributeRepository productSpecAttributeRepository, AdminVoucherService adminVoucherService)
    {
        this.productVariantRepository = productVariantRepository;
        this.productRepository = productRepository;
        this.productSpecAttributeRepository = productSpecAttributeRepository;
        this.adminVoucherService = adminVoucherService;
    }


    @Override
    public Page<ProductEntity> getProducts(ProductSearchCriteria searchCriteria)
    {
        final PageRequest pageRequest =
                PageRequest.of(searchCriteria.getPage() - 1, searchCriteria.getLimit(), Sort.by(searchCriteria.getDirection(), searchCriteria.getSort()));

        return getProductRepository().findAll(getProductSpecification(searchCriteria), pageRequest);
    }

    private Specification<ProductEntity> getProductSpecification(ProductSearchCriteria filter)
    {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getStatus() != null)
            {
                predicates.add(cb.equal(root.get("status"), ProductStatusType.valueOf(filter.getStatus())));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    @Override
    public ProductEntity getProductForId(Long productId)
    {
        final Optional<ProductEntity> productEntity = getProductRepository().findById(productId);
        if (!productEntity.isPresent())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product with id " + productId + " requested for the object does not exists in the system");
        }
        return productEntity.get();
    }

    @Override
    public List<ProductEntity> getAvailableProductsForIds(List<Long> productIds)
    {
        final List<ProductEntity> products = getProductRepository().findAllById(productIds);

        final List<ProductEntity> availableProducts
                = products.stream()
                .filter(product -> ProductStatusType.ACTIVE.equals(product.getStatus()))
                .collect(Collectors.toList());

        return availableProducts;
    }

    @Override
    public ProductSpecAttributeEntity getProductSpecAttribute(Long attributeId, ProductEntity productEntity)
    {
        for (ProductSpecAttributeEntity specAttributeEntity : productEntity.getSpecs())
        {
            if (specAttributeEntity.getId().equals(attributeId))
            {
                return specAttributeEntity;
            }
        }
        throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product spec with id " + attributeId + " requested for the object does not exists in the system");
    }

    @Override
    public ProductAttributeValueEntity getProductAttributeValue(Long attributeValueId, ProductSpecAttributeEntity productSpecAttributeEntity)
    {
        for (ProductAttributeValueEntity attributeValueEntity : productSpecAttributeEntity.getAttrValues())
        {
            if (attributeValueEntity.getId().equals(attributeValueId))
            {
                return attributeValueEntity;
            }
        }
        throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product spec value with id " + attributeValueId + " requested for the object does not exists in the system");
    }

    @Override
    public ProductVariantEntity getProductVariantForId(Long variantId)
    {
        final Optional<ProductVariantEntity> productVariantEntity = getProductVariantRepository().findById(variantId);
        if (!productVariantEntity.isPresent())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Variant with id " + variantId + " requested for the object does not exists in the system");
        }
        return productVariantEntity.get();
    }

    @Transactional
    @Override
    public void deleteAllProductSpecsAndVariantsForProduct(ProductEntity productEntity)
    {
        for (ProductSpecAttributeEntity specAttributeEntity : productEntity.getSpecs())
        {
            specAttributeEntity.getAttrValues().clear();
        }
        productEntity.getSpecs().clear();
        getModelService().removeAll(productEntity.getSpecs());
        getProductVariantRepository().deleteAllInBatch(productEntity.getVariants());
    }

    @Override
    public VoucherEntity getActiveVoucherForProduct(ProductEntity productEntity)
    {
        for (final VoucherEntity voucherEntity : productEntity.getVouchers())
        {
            final VoucherStatusType status = getAdminVoucherService().getStatusForVoucher(voucherEntity);
            if (VoucherStatusType.SCHEDULED.equals(status) || VoucherStatusType.RUNNING.equals(status))
            {
                return voucherEntity;
            }
        }
        return null;
    }


    public ProductVariantRepository getProductVariantRepository()
    {
        return productVariantRepository;
    }

    public void setProductVariantRepository(ProductVariantRepository productVariantRepository)
    {
        this.productVariantRepository = productVariantRepository;
    }

    public ProductRepository getProductRepository()
    {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public ProductSpecAttributeRepository getProductSpecAttributeRepository()
    {
        return productSpecAttributeRepository;
    }

    public void setProductSpecAttributeRepository(ProductSpecAttributeRepository productSpecAttributeRepository)
    {
        this.productSpecAttributeRepository = productSpecAttributeRepository;
    }

    public AdminVoucherService getAdminVoucherService()
    {
        return adminVoucherService;
    }

    public void setAdminVoucherService(AdminVoucherService adminVoucherService)
    {
        this.adminVoucherService = adminVoucherService;
    }
}
