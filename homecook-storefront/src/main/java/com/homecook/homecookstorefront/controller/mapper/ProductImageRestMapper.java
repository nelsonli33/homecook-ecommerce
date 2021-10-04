package com.homecook.homecookstorefront.controller.mapper;


import com.homecook.homecookstorefront.dto.ProductImageDTO;
import com.homecook.homecookstorefront.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductImageRestMapper
{
    ProductImage convertDTOtoResponse(ProductImageDTO productImageDTO);

    List<ProductImage> convertAllDTOstoResponse(List<ProductImageDTO> productImageDTOs);
}
