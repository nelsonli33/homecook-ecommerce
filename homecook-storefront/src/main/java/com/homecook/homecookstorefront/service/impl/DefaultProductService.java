package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductVariantEntity;
import com.homecook.homecookentity.repository.ProductRepository;
import com.homecook.homecookentity.type.ProductStatusType;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.ProductService;
import com.homecook.homecookstorefront.service.SKUProductFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "productService")
public class DefaultProductService implements ProductService
{
    private ProductRepository productRepository;
    private SKUProductFactory skuProductFactory;

    @Autowired
    public DefaultProductService(ProductRepository productRepository, SKUProductFactory skuProductFactory)
    {
        this.productRepository = productRepository;
        this.skuProductFactory = skuProductFactory;
    }

    @Override
    public ProductEntity getProductForId(Long productId)
    {
        Specification<ProductEntity> sf = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("id"), productId));
            predicates.add(cb.equal(root.get("status"), ProductStatusType.ACTIVE));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

        final Optional<ProductEntity> productEntity = productRepository.findOne(sf);
        if (productEntity.isPresent())
        {
            return productEntity.get();
        }
        throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product with id " + productId + " not found.");
    }

    @Override
    public ProductVariantEntity getVariantForProduct(ProductEntity product, Long variantId)
    {
        for (ProductVariantEntity variant : product.getVariants())
        {
            if (variant.getId().equals(variantId))
            {
                return variant;
            }
        }
        throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product Variant with id " + variantId + " does not exists in product with id " + product.getId());
    }

    @Override
    public SKUProduct getSKUProduct(ProductEntity product, ProductVariantEntity productVariant)
    {
        validateParameterNotNullStandardMessage("product", product);
        return getSkuProductFactory().createSKUProduct(product, productVariant);
    }


    public ProductRepository getProductRepository()
    {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public SKUProductFactory getSkuProductFactory()
    {
        return skuProductFactory;
    }

    public void setSkuProductFactory(SKUProductFactory skuProductFactory)
    {
        this.skuProductFactory = skuProductFactory;
    }
}
