package com.homecook.homecookadmin.service;

import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminProductImageService
{
    List<ProductImageEntity> getProductImagesForIds(List<Long> productImageIds);

    ProductImageEntity getProudctImageForProduct(ProductEntity productEntity, Long productImageId);

    ProductImageEntity getProductImageForId(Long productImageId);

    ProductImageEntity uploadProductImageForUrl(String imageUrl);

    ProductImageEntity uploadProductImageForMultipartFile(MultipartFile file);

    void deleteProductImageForProduct(ProductEntity productEntity, Long productImageId);

    String getImagename(String filename, int width, int height);
}
