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

    List<ProductImageDTO> getProductImages(Long productId);

    ProductImageDTO getProductImageDetail(Long productId, Long productImageId);

    ProductImageDTO createProductImage(Long productId, UploadProductImageDTO uploadProductImageDTO);

    ProductImageDTO createProductImage(Long productId, MultipartFile file);

    ProductImageDTO uploadProductImage(MultipartFile file);

    void deleteProductImage(Long productId, Long productImageId);

    List<ProductSpecDTO> getProductSpecs(Long productId);

    ProductSpecDTO getProductSpecDetail(Long productId, Long specId);

    ProductSpecDTO updateProductSpec(Long productId, Long specId, ProductSpecDTO productSpecDTO);

    void deleteAllProductSpecsAndVariants(Long productId);


}
