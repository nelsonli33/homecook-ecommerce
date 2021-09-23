package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.controller.mapper.ProductRestMapper;
import com.homecook.homecookadmin.controller.mapper.ProductSpecRestMapper;
import com.homecook.homecookadmin.dto.PageDTO;
import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.dto.ProductSearchCriteria;
import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/v1/products")
public class AdminProductController
{
    @Resource(name = "adminProductFacade")
    private AdminProductFacade adminProductFacade;

    @Autowired
    private ProductRestMapper productRestMapper;

    @Autowired
    private ProductSpecRestMapper productSpecRestMapper;

    @GetMapping
    public ResponseEntity<ProductPaginationResult> productList(
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
            @RequestParam(required = false, defaultValue = "50", value = "limit") Integer limit,
            @RequestParam(required = false, defaultValue = "DESC", value = "order") Sort.Direction direction,
            @RequestParam(required = false, defaultValue = "updatedAt", value = "sort") String sort,
            @RequestParam(required = false, value = "status") Integer status) {

        ProductSearchCriteria searchCriteria = ProductSearchCriteria.Builder.newBuilder()
                .setPage(page)
                .setLimit(limit)
                .setDirection(direction)
                .setSort(sort)
                .setStatus(status)
                .build();

        final PageDTO<ProductDTO> productPageDTO = adminProductFacade.getProducts(searchCriteria);
        final ProductPaginationResult productPaginationResult = productRestMapper.convertPageDTOtoResponse(productPageDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productPaginationResult);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductDetail(@PathVariable("productId") Long productId) {
        final ProductDTO productData = adminProductFacade.getProductDetail(productId);
        Product product = productRestMapper.convertDTOtoResponse(productData);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        ProductDTO productDTO = productRestMapper.convertRequestToDTO(createProductRequest);
        ProductDTO productData = adminProductFacade.createProduct(productDTO);
        Product product = productRestMapper.convertDTOtoResponse(productData);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(product);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody UpdateProductRequest updateProductRequest) {
        ProductDTO productDTO = productRestMapper.convertRequestToDTO(updateProductRequest);
        ProductDTO productData = adminProductFacade.updateProduct(productId, productDTO);
        Product product = productRestMapper.convertDTOtoResponse(productData);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        adminProductFacade.deleteProduct(productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @GetMapping("/{productId}/specs")
    public ResponseEntity<List<ProductSpec>> listProductSpecs(@PathVariable("productId") Long productId) {
        final List<ProductSpecDTO> productSpecDTOs = adminProductFacade.getProductSpecs(productId);
        final List<ProductSpec> productSpecs = productSpecRestMapper.convertAllDTOtoResponse(productSpecDTOs);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSpecs);
    }

    @GetMapping("/{productId}/specs/{specId}")
    public ResponseEntity<ProductSpec> getProductSpecDetail(
            @PathVariable("productId") Long productId,
            @PathVariable("specId") Long specId
    ) {
        final ProductSpecDTO productSpecDTO = adminProductFacade.getProductSpecDetail(productId, specId);
        final ProductSpec productSpec = productSpecRestMapper.convertDTOtoResponse(productSpecDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSpec);
    }

    @PutMapping("/{productId}/specs/{specId}")
    public ResponseEntity<ProductSpec> updateProductSpec(
            @PathVariable("productId") Long productId,
            @PathVariable("specId") Long specId,
            @RequestBody UpdateProductSpecRequest updateProductSpecRequest
    ) {
        final ProductSpecDTO productSpecDTO = productSpecRestMapper.convertRequestToDTO(updateProductSpecRequest);
        final ProductSpecDTO productSpecData = adminProductFacade.updateProductSpec(productId, specId, productSpecDTO);
        final ProductSpec productSpec = productSpecRestMapper.convertDTOtoResponse(productSpecData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productSpec);
    }

    @DeleteMapping("/{productId}/specs")
    public ResponseEntity<Void> deleteProductSpec(
            @PathVariable("productId") Long productId
    ) {
        adminProductFacade.deleteAllProductSpecsAndVariants(productId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }
}
