package com.homecook.homecookadmin.controller;

import com.google.cloud.storage.Blob;
import com.homecook.homecookadmin.dto.ProductImageData;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookadmin.util.FileUtil;
import com.homecook.homecookcommon.service.GCSStorageService;
import com.homecook.homecookdomain.model.ProductImageModel;
import com.homecook.homecookdomain.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/admin/api/v1/products/images")
public class AdminProductImageController
{
    private static final Logger log = LoggerFactory.getLogger(AdminProductImageController.class);

    @Resource(name = "adminProductFacade")
    private AdminProductFacade adminProductFacade;

    @Autowired
    GCSStorageService gcsStorageService;

    @PostMapping(value = "/upload")
    public ResponseEntity<ProductImageData> productImageUpload(@RequestParam("file") MultipartFile file) throws ExecutionException, InterruptedException
    {
        if (file.isEmpty())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_UPLOAD_ERROR, "Failed to upload empty file.");
        }

        FileUtil.checkValidImageFile(file);

//        final CompletableFuture<ProductImageData> future = adminProductFacade.uploadProductImage(file);

        return ResponseEntity.status(HttpStatus.OK).body(adminProductFacade.uploadProductImage(file));
    }
}
