package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminProductFacade
{
    PageDTO<ProductDTO> getProducts(ProductSearchCriteria productSearchCriteria);

    ProductDTO getProductDetail(Long productId);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long productId, ProductDTO productDTO);

    void deleteProduct(Long productId);

    List<ProductSpecDTO> getProductSpecs(Long productId);

    ProductSpecDTO getProductSpecDetail(Long productId, Long specId);

    ProductSpecDTO updateProductSpec(Long productId, Long specId, ProductSpecDTO productSpecDTO);

    void deleteAllProductSpecsAndVariants(Long productId);

    ProductImageDTO uploadProductImage(MultipartFile file);
}
