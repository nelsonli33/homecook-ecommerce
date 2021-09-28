package com.homecook.homecookadmin.facade.impl;

import com.homecook.homecookadmin.dto.*;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component(value = "adminProductFacade")
public class DefaultAdminProductFacade implements AdminProductFacade
{
    private static final Logger log = LoggerFactory.getLogger(DefaultAdminProductFacade.class);

    @Value("${product.image.max.limit}")
    private Integer productImageUploadLimit;

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
            AdminProductImageMapper adminProductImageMapper,
            AdminProductMapper adminProductMapper,
            AdminProductSpecMapper adminProductSpecMapper
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
        validateUpdateProduct(productDTO, productEntity);
        adminProductMapper.populateToProductEntity(productDTO, productEntity);
        getModelService().save(productEntity);
        return adminProductMapper.convertToProductDTO(productEntity);
    }

    private void validateUpdateProduct(ProductDTO productDTO, ProductEntity productEntity)
    {
        final List<Long> productImageIds = productDTO.getProductImageIds();
        final List<ProductImageEntity> images = productEntity.getImages();
        final int imageCountAfterUpdate = productImageIds.size() + images.size();

        if (imageCountAfterUpdate > productImageUploadLimit) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_ADD_MORE_THEN_LIMIT, "Added up to "+productImageUploadLimit+" product images");
        }

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
    public List<ProductImageDTO> getProductImages(Long productId) {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        return adminProductImageMapper.convertAllToProductImageDTOs(productEntity.getImages());
    }

    @Override
    public ProductImageDTO getProductImageDetail(Long productId, Long productImageId)
    {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        final ProductImageEntity productImageEntity = adminProductImageService.getProudctImageForProduct(productEntity, productImageId);
        return adminProductImageMapper.convertToProductImageDTO(productImageEntity);
    }

    @Override
    public ProductImageDTO createProductImage(Long productId, UploadProductImageDTO uploadProductImageDTO)
    {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        int imageCountAfterUpdate = productEntity.getImages().size() + 1;
        if (imageCountAfterUpdate > productImageUploadLimit) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_ADD_MORE_THEN_LIMIT, "Added up to "+productImageUploadLimit+" product images");
        }

        ProductImageEntity productImageEntity = null;

        if (uploadProductImageDTO != null && StringUtils.isNotEmpty(uploadProductImageDTO.getImageUrl())) {
            productImageEntity = adminProductImageService.uploadProductImageForUrl(uploadProductImageDTO.getImageUrl());
        }

        if (productImageEntity != null) {
            productImageEntity.setProduct(productEntity);
            getModelService().save(productImageEntity);
        }
        return adminProductImageMapper.convertToProductImageDTO(productImageEntity);
    }

    @Override
    public ProductImageDTO createProductImage(Long productId, MultipartFile file)
    {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        int imageCountAfterUpdate = productEntity.getImages().size() + 1;
        if (imageCountAfterUpdate > productImageUploadLimit) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_ADD_MORE_THEN_LIMIT, "Added up to "+productImageUploadLimit+" product images");
        }

        ProductImageEntity productImageEntity = adminProductImageService.uploadProductImageForMultipartFile(file);
        if (productImageEntity != null) {
            productImageEntity.setProduct(productEntity);
            getModelService().save(productImageEntity);
        }
        return adminProductImageMapper.convertToProductImageDTO(productImageEntity);
    }

    @Override
    public ProductImageDTO uploadProductImage(MultipartFile file) {
        final ProductImageEntity productImageEntity = adminProductImageService.uploadProductImageForMultipartFile(file);
        return adminProductImageMapper.convertToProductImageDTO(productImageEntity);
    }

    @Override
    public void deleteProductImage(Long productId, Long productImageId) {
        final ProductEntity productEntity = adminProductService.getProductForId(productId);
        adminProductImageService.deleteProductImageForProduct(productEntity, productImageId);
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
