package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.controller.mapper.ProductImageRestMapper;
import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookadmin.dto.UploadProductImageDTO;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.model.CreateProductImageRequest;
import com.homecook.homecookadmin.model.ProductImage;
import com.homecook.homecookadmin.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/v1/products")
public class AdminProductImageController
{
    private static final Logger log = LoggerFactory.getLogger(AdminProductImageController.class);

    private AdminProductFacade adminProductFacade;
    private ProductImageRestMapper productImageRestMapper;

    @Autowired
    public AdminProductImageController(
            @Qualifier(value = "adminProductFacade") AdminProductFacade adminProductFacade,
            ProductImageRestMapper productImageRestMapper)
    {
        this.adminProductFacade = adminProductFacade;
        this.productImageRestMapper = productImageRestMapper;
    }

    @GetMapping(value = "/{productId}/images")
    public ResponseEntity<List<ProductImage>> listProductImages(@PathVariable("productId") Long productId)
    {
        final List<ProductImageDTO> productImageData = adminProductFacade.getProductImages(productId);
        final List<ProductImage> productImages = productImageRestMapper.convertAllDTOstoResponse(productImageData);
        return ResponseEntity.status(HttpStatus.OK).body(productImages);
    }

    @GetMapping(value = "/{productId}/images/{imageId}")
    public ResponseEntity<ProductImage> getProductImage(@PathVariable("productId") Long productId,
                                                        @PathVariable("imageId") Long imageId)
    {
        final ProductImageDTO productImageData = adminProductFacade.getProductImageDetail(productId, imageId);
        final ProductImage productImage = productImageRestMapper.convertDTOtoResponse(productImageData);
        return ResponseEntity.status(HttpStatus.OK).body(productImage);
    }

    @PostMapping(value = "/{productId}/images", consumes = "application/json")
    public ResponseEntity<ProductImage> createProductImageForUrl(
            @PathVariable("productId") Long productId,
            @RequestBody CreateProductImageRequest createProductImageRequest
    )
    {
        final UploadProductImageDTO uploadProductImageDTO
                = productImageRestMapper.convertRequestToDTO(createProductImageRequest);
        final ProductImageDTO productImageData =
                adminProductFacade.createProductImage(productId, uploadProductImageDTO);
        final ProductImage productImage = productImageRestMapper.convertDTOtoResponse(productImageData);
        return ResponseEntity.status(HttpStatus.OK).body(productImage);
    }

    @PostMapping(value = "/{productId}/images", consumes = "multipart/form-data")
    public ResponseEntity<ProductImage> createProductImageForMultipartFile(
            @PathVariable("productId") Long productId,
            @RequestParam("file") MultipartFile file
    )
    {
        final ProductImageDTO productImageData =
                adminProductFacade.createProductImage(productId, file);
        final ProductImage productImage = productImageRestMapper.convertDTOtoResponse(productImageData);
        return ResponseEntity.status(HttpStatus.OK).body(productImage);
    }


    @DeleteMapping(value = "/{productId}/images/{imageId}")
    public ResponseEntity<?> deleteProductImage(@PathVariable("productId") Long productId,
                                                @PathVariable("imageId") Long imageId)
    {
        adminProductFacade.deleteProductImage(productId, imageId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PostMapping(value = "/products/images/upload")
    public ResponseEntity<ProductImage> productImageUpload(@RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_UPLOAD_ERROR, "Failed to upload empty file.");
        }

        FileUtil.checkValidImageFile(file);

        final ProductImageDTO productImageData = adminProductFacade.uploadProductImage(file);
        final ProductImage productImage = productImageRestMapper.convertDTOtoResponse(productImageData);
        return ResponseEntity.status(HttpStatus.OK).body(productImage);
    }
}
