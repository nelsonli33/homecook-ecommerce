package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.*;
import com.homecook.homecookentity.type.ProductStatusType;
import com.homecook.homecookstorefront.dto.*;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ProductImageMapper.class}
)
public abstract class ProductMapper
{

    public abstract ProductDTO convertToProductDTO(ProductEntity productEntity);

    public abstract List<ProductDTO> convertAllToProductDTO(List<ProductEntity> productEntities);

    @Mappings({
            @Mapping(target = "sortOrder", ignore = true),
            @Mapping(target = "metaTitle", ignore = true),
            @Mapping(target = "metaDescription", ignore = true)
    })
    public abstract CategoryDTO convertToCategoryDTO(CategoryEntity categoryEntity);

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


    protected Integer getValueForProductStatusType(ProductStatusType productStatusType)
    {
        return productStatusType.getValue();
    }

}
