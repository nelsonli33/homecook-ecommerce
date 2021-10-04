package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.ProductRestMapper;
import com.homecook.homecookstorefront.dto.ProductDTO;
import com.homecook.homecookstorefront.facade.ProductFacade;
import com.homecook.homecookstorefront.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController
{
    private ProductFacade productFacade;
    private ProductRestMapper productRestMapper;

    @Autowired
    public ProductController(ProductFacade productFacade, ProductRestMapper productRestMapper)
    {
        this.productFacade = productFacade;
        this.productRestMapper = productRestMapper;
    }

    @RequestMapping(value = "/api/v1/products/{productId}")
    public ResponseEntity<Product> getProductDetail(@PathVariable("productId") Long productId)
    {
        final ProductDTO productDTO = productFacade.getProductDetails(productId);
        final Product product = productRestMapper.convertDTOtoResponse(productDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }
}
