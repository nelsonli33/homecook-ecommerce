package com.homecook.homecookadmin.controller;

import com.homecook.homecookadmin.dto.ProductImageData;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.util.FileUtil;
import com.homecook.homecookcommon.service.GCSStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

        return ResponseEntity.status(HttpStatus.OK).body(adminProductFacade.uploadProductImage(file));
    }
}
