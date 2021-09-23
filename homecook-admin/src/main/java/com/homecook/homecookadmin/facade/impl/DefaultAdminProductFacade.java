package com.homecook.homecookadmin.facade.impl;

import com.google.cloud.storage.Blob;
import com.homecook.homecookadmin.dto.*;
import com.homecook.homecookadmin.facade.AdminProductFacade;
import com.homecook.homecookadmin.facade.mapper.AdminProductImageMapper;
import com.homecook.homecookadmin.facade.mapper.AdminProductMapper;
import com.homecook.homecookadmin.facade.mapper.AdminProductSpecMapper;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookadmin.service.AdminProductService;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.ProductImageEntity;
import com.homecook.homecookentity.entity.ProductSpecAttributeEntity;
import com.homecook.homecookentity.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component(value = "adminProductFacade")
public class DefaultAdminProductFacade implements AdminProductFacade
{
    private static final Logger log = LoggerFactory.getLogger(DefaultAdminProductFacade.class);

    private AdminProductImageService adminProductImageService;
    private AdminProductService adminProductService;
    private ModelService modelService;
    private AdminProductImageMapper adminProductImageMapper;
    private AdminProductMapper adminProductMapper;
    private AdminProductSpecMapper adminProductSpecMapper;


    @Autowired
    public DefaultAdminProductFacade(
            @Qualifier(value = "adminProductImageService") AdminProductImageService adminProductImageService,
            @Qualifier(value = "adminProductService") AdminProductService adminProductService,
            @Qualifier(value = "modelService") ModelService modelService,
            @Autowired AdminProductImageMapper adminProductImageMapper,
            @Autowired AdminProductMapper adminProductMapper,
            @Autowired AdminProductSpecMapper adminProductSpecMapper
    )
    {
        this.adminProductImageService = adminProductImageService;
        this.adminProductService = adminProductService;
        this.modelService = modelService;
        this.adminProductImageMapper = adminProductImageMapper;
        this.adminProductMapper = adminProductMapper;
        this.adminProductSpecMapper = adminProductSpecMapper;
    }

    @Override
    public PageDTO<ProductDTO> getProducts(ProductSearchCriteria productSearchCriteria)
    {
        final Page<ProductEntity> products = adminProductService.getProducts(productSearchCriteria);

        PageDTO<ProductDTO> productPageDTO = new PageDTO<>();
        productPageDTO.setTotal(products.getNumberOfElements());
        productPageDTO.setCurrentPage(productSearchCriteria.getPage());
        productPageDTO.setPageSize(productSearchCriteria.getLimit());
        productPageDTO.setPageCount(products.getTotalPages());
        productPageDTO.setResult(adminProductMapper.convertAllToProductDTO(products.getContent()));

        return productPageDTO;
    }

    @Override
    public ProductDTO getProductDetail(Long productId) {
        return adminProductMapper.convertToProductDTO(adminProductService.getProductForId(productId));
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        ProductEntity productEntity = adminProductMapper.convertToProductEntity(productDTO);
        getModelService().save(productEntity);
        return adminProductMapper.convertToProductDTO(productEntity);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO)
    {
        ProductEntity productEntity = adminProductService.getProductForId(productId);
        adminProductMapper.populateToProductEntity(productDTO, productEntity);
        getModelService().save(productEntity);
        return adminProductMapper.convertToProductDTO(productEntity);
    }

    @Override
    public void deleteProduct(Long productId)
    {
        ProductEntity product = adminProductService.getProductForId(productId);
        getModelService().remove(product);
    }

    @Override
    public List<ProductSpecDTO> getProductSpecs(Long productId)
    {
        ProductEntity productEntity = adminProductService.getProductForId(productId);
        return adminProductSpecMapper.convertAllToProdutSpecDTOs(productEntity.getSpecs());
    }

    @Override
    public ProductSpecDTO getProductSpecDetail(Long productId, Long specId)
    {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        final ProductSpecAttributeEntity productSpecAttributeEntity =
                adminProductService.getProductSpecAttribute(specId, productEntity);
        return adminProductSpecMapper.convertToProductSpecDTO(productSpecAttributeEntity);
    }

    @Override
    public ProductSpecDTO updateProductSpec(Long productId, Long specId, ProductSpecDTO productSpecDTO)
    {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        final ProductSpecAttributeEntity productSpecAttributeEntity =
                adminProductService.getProductSpecAttribute(specId, productEntity);
        adminProductSpecMapper.populateToProductSpecAttributeEntity(productSpecDTO, productSpecAttributeEntity);
        getModelService().save(productSpecAttributeEntity);
        return adminProductSpecMapper.convertToProductSpecDTO(productSpecAttributeEntity);
    }


    @Override
    public void deleteAllProductSpecsAndVariants(Long productId) {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        adminProductService.deleteAllProductSpecsAndVariantsForProduct(productEntity);
    }


    @Override
    public ProductImageDTO uploadProductImage(MultipartFile file) {
        final List<Blob> blobs =
                adminProductImageService.uploadProductImage(file);

        sortBlobsByImageWidthDESC(blobs);


        ProductImageDTO productImageDTO = null;
        // if the number of uploaded images is not equal 4, then we will use cron job to remove the images not assigned to specific product.
        if(blobs.size() == 4) {
            ProductImageEntity entity = new ProductImageEntity();
            entity.setCode(blobs.get(0).getName());
            entity.setFilename(blobs.get(0).getName());
            entity.setOriginfilename(file.getOriginalFilename());

            final Map<String, String> metadata = blobs.get(0).getMetadata();
            final String zoomImageName
                    = adminProductImageService.getImagename(blobs.get(0).getName(), Integer.parseInt(metadata.get("width")), Integer.parseInt(metadata.get("height")));
            entity.setZoom(zoomImageName);
            entity.setDetail(blobs.get(1).getName());
            entity.setNormal(blobs.get(2).getName());
            entity.setThumbnail(blobs.get(3).getName());

            modelService.save(entity);
            productImageDTO = adminProductImageMapper.convertToProductImageDTO(entity);
        }
        return productImageDTO;
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
