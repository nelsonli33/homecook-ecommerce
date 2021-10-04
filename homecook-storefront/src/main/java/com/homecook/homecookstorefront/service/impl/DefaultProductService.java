package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.repository.ProductRepository;
import com.homecook.homecookentity.type.ProductStatusType;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "productService")
public class DefaultProductService implements ProductService
{
    private ProductRepository productRepository;

    @Autowired
    public DefaultProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity getProductForId(Long productId)
    {
        final Optional<ProductEntity> productEntity = productRepository.findOne(getProductSpecification(productId));
        if (productEntity.isPresent())
        {
            return productEntity.get();
        }
        throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Product with id " + productId + " not found.");
    }


    private Specification<ProductEntity> getProductSpecification(Long productId)
    {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("id"), productId));
            predicates.add(cb.equal(root.get("status"), ProductStatusType.ACTIVE));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
