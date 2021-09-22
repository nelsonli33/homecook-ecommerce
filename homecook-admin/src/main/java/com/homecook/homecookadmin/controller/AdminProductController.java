package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.controller.mapper.ProductRestMapper;
import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.model.CreateProductRequest;
import com.homecook.homecookadmin.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/admin/api/v1/products")
public class AdminProductController
{
    @Resource(name = "adminProductFacade")
    private AdminProductFacade adminProductFacade;

    @Autowired
    private ProductRestMapper productRestMapper;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        ProductDTO productDTO = productRestMapper.convertRequestToDTO(createProductRequest);
        ProductDTO productData = adminProductFacade.createProduct(productDTO);
        Product product = productRestMapper.convertDTOtoResponse(productData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }
}
