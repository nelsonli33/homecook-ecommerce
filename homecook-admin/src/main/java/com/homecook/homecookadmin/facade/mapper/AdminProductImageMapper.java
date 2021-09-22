package com.homecook.homecookadmin.facade.mapper;

import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookentity.entity.ProductImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AdminProductImageMapper
{
    public abstract ProductImageDTO convertToProductImageDTO(ProductImageEntity productImageEntity);
}
