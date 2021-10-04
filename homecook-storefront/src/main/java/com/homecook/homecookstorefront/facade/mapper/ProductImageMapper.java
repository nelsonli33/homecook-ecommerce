package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.ProductImageEntity;
import com.homecook.homecookstorefront.dto.ProductImageDTO;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class ProductImageMapper
{
    @Value("${cloud.google.storage.url}")
    private String storageURL = "";

    @Mappings({
            @Mapping(source = "thumbnail", target = "thumbnailUrl", qualifiedByName = "convertToImageURL"),
            @Mapping(source = "normal", target = "normalUrl", qualifiedByName = "convertToImageURL"),
            @Mapping(source = "detail", target = "detailUrl", qualifiedByName = "convertToImageURL"),
            @Mapping(source = "filename", target = "zoomUrl", qualifiedByName = "convertToImageURL"),
    })
    public abstract ProductImageDTO convertToProductImageDTO(ProductImageEntity productImageEntity);

    public abstract List<ProductImageDTO> convertAllToProductImageDTOs(List<ProductImageEntity> productImageEntities);

    @Named("convertToImageURL")
    protected String convertToImageURL(String objectName)
    {
        return storageURL + "/" + objectName;
    }
}
