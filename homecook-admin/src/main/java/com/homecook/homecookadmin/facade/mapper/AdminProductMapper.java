package com.homecook.homecookadmin.facade.mapper;

import com.google.common.collect.Sets;
import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.dto.ProductSpecValueDTO;
import com.homecook.homecookadmin.dto.ProductVariantDTO;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookadmin.service.AdminProductImageService;
import com.homecook.homecookadmin.service.AdminProductService;
import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.type.ProductStatusType;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AdminCategoryMapper.class}
)
public abstract class AdminProductMapper
{
    private AdminCategoryService adminCategoryService;
    private AdminProductService adminProductService;
    private AdminProductImageService adminProductImageService;

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "addProductCategories"),
            @Mapping(target = "specs", ignore = true), // mapped in AfterMapping
            @Mapping(target = "variants", ignore = true) // mapped in AfterMapping
    })
    public abstract ProductEntity convertToProductEntity(ProductDTO productDTO);


    @Named("addProductCategories")
    protected Set<CategoryEntity> addProductCategories(List<Long> categoryIds)
    {
        if (CollectionUtils.isNotEmpty(categoryIds))
        {
            List<CategoryEntity> categoryEntities = adminCategoryService.getCategoriesForIds(categoryIds);
            Set<CategoryEntity> categoryEntitySet = Sets.newHashSet(categoryEntities);
            return categoryEntitySet;
        }
        return null;
    }

    @AfterMapping
    protected void addProductImages(ProductDTO productDTO, @MappingTarget ProductEntity productEntity)
    {
        final List<Long> productImageIds = productDTO.getProductImageIds();
        if (CollectionUtils.isEmpty(productImageIds))
        {
            return;
        }

        final List<ProductImageEntity> productImageEntities = getAdminProductImageService().getProductImagesForIds(productImageIds);
        if (productEntity.getId() == null)
        {
            for (int i = 0; i < productImageEntities.size(); i++)
            {
                ProductImageEntity productImageEntity = productImageEntities.get(i);
                productImageEntity.setProduct(productEntity);
                productImageEntity.setSortOrder(i + 1);
            }
            productEntity.setImages(productImageEntities);
        }
        else
        {
            for (int i = 0; i < productImageEntities.size(); i++)
            {
                ProductImageEntity productImageEntity = productImageEntities.get(i);
                if (productImageEntity.getProduct() != null && !productImageEntity.getProduct().getId().equals(productEntity.getId()))
                {
                    throw new HomecookAdminRuntimeException(InternalErrorCode.PRODUCT_IMAGE_ALREADY_IN_USE, "Product Image with id: " + productImageEntity.getId() + "already in use.");
                }
                productImageEntity.setProduct(productEntity);
                productImageEntity.setSortOrder(i + 1);
            }
            productEntity.setImages(productImageEntities);
        }
    }

    @AfterMapping
    protected void addProductSpecsForProduct(ProductDTO productDTO, @MappingTarget ProductEntity productEntity)
    {
        List<ProductSpecDTO> specDTOs = productDTO.getSpecs();
        if (CollectionUtils.isNotEmpty(specDTOs))
        {

            List<ProductSpecAttributeEntity> specAttributeEntities = new ArrayList<>();

            int specOrder = 1;

            for (ProductSpecDTO specDTO : specDTOs)
            {
                ProductSpecAttributeEntity specAttributeEntity;

                if (specDTO.getId() != null)
                {
                    specAttributeEntity = adminProductService.getProductSpecAttribute(specDTO.getId(), productEntity);
                }
                else
                {
                    specAttributeEntity = new ProductSpecAttributeEntity();
                }

                if (specDTO.getName() != null)
                {
                    specAttributeEntity.setName(specDTO.getName());
                }

                specAttributeEntity.setSortOrder(specOrder++);
                specAttributeEntity.setProduct(productEntity);


                List<ProductSpecValueDTO> specValues = specDTO.getValues();
                if (CollectionUtils.isNotEmpty(specValues))
                {

                    List<ProductAttributeValueEntity> attributeValueEntities = new ArrayList<>();

                    for (ProductSpecValueDTO specValueDTO : specValues)
                    {
                        ProductAttributeValueEntity attributeValueEntity;

                        if (specValueDTO.getId() != null)
                        {
                            attributeValueEntity = adminProductService.getProductAttributeValue(specValueDTO.getId(), specAttributeEntity);
                        }
                        else
                        {
                            attributeValueEntity = new ProductAttributeValueEntity();
                        }

                        if (specValueDTO.getValue() != null)
                        {
                            attributeValueEntity.setValue(specValueDTO.getValue());
                        }
                        attributeValueEntity.setAttribute(specAttributeEntity);

                        attributeValueEntities.add(attributeValueEntity);
                    }

                    specAttributeEntity.getAttrValues().clear();
                    specAttributeEntity.getAttrValues().addAll(attributeValueEntities);
                    specAttributeEntities.add(specAttributeEntity);
                }
            }
            productEntity.getSpecs().clear();
            productEntity.getSpecs().addAll(specAttributeEntities);
        }
    }

    @AfterMapping
    protected void addProductVariantsForProduct(ProductDTO productDTO, @MappingTarget ProductEntity productEntity)
    {
        List<ProductVariantDTO> variantDTOs = productDTO.getVariants();
        if (CollectionUtils.isEmpty(variantDTOs))
        {
            List<ProductVariantEntity> productVariantEntities = new ArrayList<>();
            final ProductVariantEntity defaultVariant = createDefaultProductVariant(productDTO, productEntity);
            productVariantEntities.add(defaultVariant);
            productEntity.getVariants().clear();
            productEntity.getVariants().addAll(productVariantEntities);
            return;
        }

        Map<String, ProductAttributeValueEntity> attrValueMap = new HashMap<>();
        for (int i = 0; i < productEntity.getSpecs().size(); i++)
        {
            final ProductAttributeEntity productAttributeEntity =
                    productEntity.getSpecs().get(i);
            productAttributeEntity.getAttrValues().forEach(attributeValueEntity -> attrValueMap.put(attributeValueEntity.getValue(), attributeValueEntity));
        }

        List<ProductVariantEntity> productVariantEntities = new ArrayList<>();
        for (int i = 0; i < variantDTOs.size(); i++)
        {
            ProductVariantDTO productVariantDTO = variantDTOs.get(i);

            ProductVariantEntity productVariantEntity;
            if (productVariantDTO.getId() != null)
            {
                productVariantEntity = adminProductService.getProductVariantForId(productVariantDTO.getId());
            }
            else
            {
                productVariantEntity = new ProductVariantEntity();

                if (productVariantDTO.getSpecValue1() != null)
                {
                    productVariantEntity.setSpecValue1(attrValueMap.get(productVariantDTO.getSpecValue1()));
                }
                if (productVariantDTO.getSpecValue2() != null)
                {
                    productVariantEntity.setSpecValue2(attrValueMap.get(productVariantDTO.getSpecValue2()));
                }
                if (productVariantDTO.getSpecValue3() != null)
                {
                    productVariantEntity.setSpecValue3(attrValueMap.get(productVariantDTO.getSpecValue3()));
                }
            }

            if (productVariantDTO.getPrice() != null)
            {
                productVariantEntity.setPrice(productVariantDTO.getPrice().doubleValue());
            }
            if (productVariantDTO.getQuantity() != null)
            {
                productVariantEntity.setQuantity(productVariantDTO.getQuantity());
            }
            if (productVariantDTO.getSku() != null)
            {
                productVariantEntity.setSku(productVariantDTO.getSku());
            }

            productVariantEntity.setProduct(productEntity);
            productVariantEntities.add(productVariantEntity);
        }

        productEntity.getVariants().clear();
        productEntity.getVariants().addAll(productVariantEntities);
    }

    public ProductVariantEntity createDefaultProductVariant(ProductDTO productDTO, ProductEntity productEntity)
    {
        ProductVariantEntity variant = new ProductVariantEntity();
        if (productDTO.getPrice() != null)
        {
            variant.setPrice(productDTO.getPrice().doubleValue());
        }
        if (productDTO.getQuantity() != null)
        {
            variant.setQuantity(productDTO.getQuantity());
        }
        if (productDTO.getSku() != null)
        {
            variant.setSku(productDTO.getSku());
        }

        variant.setProduct(productEntity);
        if (productEntity.getImages() != null && CollectionUtils.isNotEmpty(productEntity.getImages()))
        {
            variant.setImage(productEntity.getImages().get(0));
        }

        return variant;
    }

    @InheritConfiguration
    public abstract void populateToProductEntity(ProductDTO productDTO, @MappingTarget ProductEntity productEntity);


    protected ProductStatusType getProductStatusTypeForValue(Integer value)
    {
        return ProductStatusType.valueOf(value);
    }

    protected Integer getValueForProductStatusType(ProductStatusType productStatusType)
    {
        return productStatusType.getValue();
    }


    public abstract ProductDTO convertToProductDTO(ProductEntity productEntity);

    public abstract List<ProductDTO> convertAllToProductDTO(List<ProductEntity> productEntities);


    @Mapping(target = "values", ignore = true)
    public abstract ProductSpecDTO convertToProductSpecDTO(ProductSpecAttributeEntity attributeEntity);

    @AfterMapping
    protected void addProductSpecAttributeValue(ProductSpecAttributeEntity attributeEntity, @MappingTarget ProductSpecDTO target)
    {
        List<ProductSpecValueDTO> specValueDTOs = new ArrayList<>();

        for (ProductAttributeValueEntity attributeValueEntity : attributeEntity.getAttrValues())
        {
            ProductSpecValueDTO specValueDTO = new ProductSpecValueDTO();
            specValueDTO.setId(attributeValueEntity.getId());
            specValueDTO.setValue(attributeValueEntity.getValue());
            specValueDTOs.add(specValueDTO);
        }
        target.setValues(specValueDTOs);
    }

    @Mappings({
            @Mapping(source = "productVariantEntity", target = "name", qualifiedByName = "variantName"),
            @Mapping(source = "specValue1.id", target = "specValue1Id"),
            @Mapping(source = "specValue1.value", target = "specValue1"),
            @Mapping(source = "specValue2.id", target = "specValue2Id"),
            @Mapping(source = "specValue2.value", target = "specValue2"),
            @Mapping(source = "specValue3.id", target = "specValue3Id"),
            @Mapping(source = "specValue3.value", target = "specValue3"),
    })
    public abstract ProductVariantDTO convertToProductVariantDTO(ProductVariantEntity productVariantEntity);


    @Named("variantName")
    public String getVariantName(ProductVariantEntity productVariantEntity)
    {
        StringBuilder builder = new StringBuilder();

        final String productName = productVariantEntity.getProduct().getName();
        builder.append(productName);

        ProductAttributeValueEntity specValue1 = productVariantEntity.getSpecValue1();
        if (specValue1 != null)
        {
            builder.append(specValue1.getValue());
        }
        ProductAttributeValueEntity specValue2 = productVariantEntity.getSpecValue2();
        if (specValue2 != null)
        {
            builder.append(",").append(specValue2.getValue());
        }
        ProductAttributeValueEntity specValue3 = productVariantEntity.getSpecValue3();
        if (specValue3 != null)
        {
            builder.append(",").append(specValue3.getValue());
        }

        return builder.toString();
    }

    public AdminCategoryService getAdminCategoryService()
    {
        return adminCategoryService;
    }

    @Autowired
    public void setAdminCategoryService(AdminCategoryService adminCategoryService)
    {
        this.adminCategoryService = adminCategoryService;
    }

    public AdminProductService getAdminProductService()
    {
        return adminProductService;
    }

    @Autowired
    public void setAdminProductService(AdminProductService adminProductService)
    {
        this.adminProductService = adminProductService;
    }

    public AdminProductImageService getAdminProductImageService()
    {
        return adminProductImageService;
    }

    @Autowired
    public void setAdminProductImageService(AdminProductImageService adminProductImageService)
    {
        this.adminProductImageService = adminProductImageService;
    }
}
