package com.homecook.homecookadmin.facade.mapper;

import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookentity.entity.ProductImageEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AdminProductImageMapper
{
    @Value("${cloud.google.storage.url}")
    private String storageURL = "";

    @Mappings({
            @Mapping(source = "thumbnail", target = "thumbnailUrl", qualifiedByName = "convertToImageURL"),
            @Mapping(source = "normal", target = "normalUrl", qualifiedByName = "convertToImageURL"),
            @Mapping(source = "detail", target = "detailUrl", qualifiedByName = "convertToImageURL"),
    })
    public abstract ProductImageDTO convertToProductImageDTO(ProductImageEntity productImageEntity);

    public abstract List<ProductImageDTO> convertAllToProductImageDTOs(List<ProductImageEntity> productImageEntities);

    @Named("convertToImageURL")
    protected String convertToImageURL(String objectName) {
        return storageURL+ "/" +objectName;
    }
}
