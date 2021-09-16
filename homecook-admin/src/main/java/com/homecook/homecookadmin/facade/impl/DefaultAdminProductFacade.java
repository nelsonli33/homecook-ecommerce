package com.homecook.homecookadmin.facade.impl;

import com.google.cloud.storage.Blob;
import com.homecook.homecookadmin.controller.AdminProductImageController;
import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookadmin.dto.ProductImageData;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookdomain.model.CategoryModel;
import com.homecook.homecookdomain.model.ProductImageModel;
import com.homecook.homecookdomain.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component(value = "adminProductFacade")
public class DefaultAdminProductFacade implements AdminProductFacade
{
    private static final Logger log = LoggerFactory.getLogger(DefaultAdminProductFacade.class);

    private AdminProductImageService adminProductImageService;
    private ModelService modelService;
    private Converter<ProductImageModel, ProductImageData> adminProductImageConverter;

    @Autowired
    public DefaultAdminProductFacade(
            @Qualifier(value = "adminProductImageService") AdminProductImageService adminProductImageService,
            @Qualifier(value = "modelService") ModelService modelService,
            @Qualifier(value = "adminProductImageConverter") Converter<ProductImageModel, ProductImageData> adminProductImageConverter
    )
    {
        this.adminProductImageService = adminProductImageService;
        this.modelService = modelService;
        this.adminProductImageConverter = adminProductImageConverter;
    }

    public ProductImageData uploadProductImage(MultipartFile file) {
        final List<Blob> blobs =
                adminProductImageService.uploadProductImage(file);

        sortBlobsByImageWidthDESC(blobs);


        ProductImageData productImageData = null;
        // if the number of uploaded images is not equal 4, then we will use cron job to remove the images not assigned to specific product.
        if(blobs.size() == 4) {
            ProductImageModel model = new ProductImageModel();
            model.setFilename(blobs.get(0).getName());
            model.setOriginfilename(file.getOriginalFilename());

            final Map<String, String> metadata = blobs.get(0).getMetadata();
            final String zoomImageName
                    = adminProductImageService.getImagename(blobs.get(0).getName(), Integer.parseInt(metadata.get("width")), Integer.parseInt(metadata.get("height")));
            model.setZoom(zoomImageName);
            model.setDetail(blobs.get(1).getName());
            model.setNormal(blobs.get(2).getName());
            model.setThumbnail(blobs.get(3).getName());

            modelService.save(model);
            productImageData = adminProductImageConverter.convert(model);
        }
        return productImageData;
    }


    private void sortBlobsByImageWidthDESC(List<Blob> blobs) {
        Collections.sort(blobs, (o1, o2) -> {
            final Map<String, String> o1Metadata = o1.getMetadata();
            final Map<String, String> o2Metadata = o2.getMetadata();

            int o1Width = Integer.parseInt(o1Metadata.get("width"));
            int o2Width = Integer.parseInt(o2Metadata.get("width"));

            return o2Width - o1Width;
        });
    }


    public AdminProductImageService getAdminProductImageService()
    {
        return adminProductImageService;
    }

    public void setAdminProductImageService(AdminProductImageService adminProductImageService)
    {
        this.adminProductImageService = adminProductImageService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
