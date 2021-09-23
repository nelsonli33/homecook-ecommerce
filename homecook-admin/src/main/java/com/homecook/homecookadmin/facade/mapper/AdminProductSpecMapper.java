package com.homecook.homecookadmin.facade.mapper;

import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.dto.ProductSpecValueDTO;
import com.homecook.homecookentity.entity.ProductAttributeValueEntity;
import com.homecook.homecookentity.entity.ProductSpecAttributeEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AdminProductSpecMapper
{
    @Mapping(source = "attrValues", target = "values")
    public abstract  ProductSpecDTO convertToProductSpecDTO(ProductSpecAttributeEntity productSpecAttributeEntity);

    @Mapping(target = "attrValues", ignore = true)
    public abstract void populateToProductSpecAttributeEntity(ProductSpecDTO productSpecDTO, @MappingTarget ProductSpecAttributeEntity productSpecAttributeEntity);

    @AfterMapping
    public void setAttributeValues(ProductSpecDTO productSpecDTO, @MappingTarget ProductSpecAttributeEntity productSpecAttributeEntity) {
        if ( productSpecAttributeEntity.getAttrValues() != null ) {
            final Map<Long, ProductSpecValueDTO>
                    map = productSpecDTO.getValues().stream().collect(Collectors.toMap(ProductSpecValueDTO::getId, item -> item));

            final List<ProductAttributeValueEntity> list = productSpecAttributeEntity.getAttrValues().stream()
                    .map(attributeValueEntity -> {
                        populateToProductAttributeValueEntity(map.get(attributeValueEntity.getId()), attributeValueEntity);
                        return attributeValueEntity;
                    })
                    .collect(Collectors.toList());

            if ( list != null ) {
                productSpecAttributeEntity.getAttrValues().clear();
                productSpecAttributeEntity.getAttrValues().addAll( list );
            }
        }
        else {
            List<ProductAttributeValueEntity> list = convertAllDTOToProductAttributeValueEntities( productSpecDTO.getValues() );
            list.forEach((productAttributeValueEntity) -> productAttributeValueEntity.setAttribute(productSpecAttributeEntity));
            if ( list != null ) {
                productSpecAttributeEntity.setAttrValues( list );
            }
        }
    }

    public abstract void populateToProductAttributeValueEntity(ProductSpecValueDTO productSpecValueDTO, @MappingTarget ProductAttributeValueEntity productAttributeValueEntity);

    public abstract ProductAttributeValueEntity convertDTOToProductAttributeValueEntity(ProductSpecValueDTO productSpecValueDTO);

    public abstract List<ProductAttributeValueEntity> convertAllDTOToProductAttributeValueEntities(List<ProductSpecValueDTO> productSpecValueDTO);

    public abstract ProductSpecValueDTO convertToProductSpecValueDTO(ProductAttributeValueEntity attrValue);

    public abstract List<ProductSpecDTO> convertAllToProdutSpecDTOs(List<ProductSpecAttributeEntity> productSpecAttributeEntities);

    public abstract List<ProductSpecValueDTO> convertAllToProductSpecValueDTOs(List<ProductAttributeValueEntity> attrValues);
}
