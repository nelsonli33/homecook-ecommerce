package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookstorefront.dto.ProductDTO;
import com.homecook.homecookstorefront.facade.ProductFacade;
import com.homecook.homecookstorefront.facade.mapper.ProductMapper;
import com.homecook.homecookstorefront.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "productFacade")
public class DefaultProductFacade implements ProductFacade
{
    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public DefaultProductFacade(ProductService productService, ProductMapper productMapper)
    {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO getProductDetails(Long productId)
    {
        final ProductEntity productEntity = productService.getProductForId(productId);
        return productMapper.convertToProductDTO(productEntity);
    }
}
