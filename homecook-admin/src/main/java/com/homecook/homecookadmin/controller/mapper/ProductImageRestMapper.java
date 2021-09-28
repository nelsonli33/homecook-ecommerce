package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookadmin.dto.UploadProductImageDTO;
import com.homecook.homecookadmin.model.CreateProductImageRequest;
import com.homecook.homecookadmin.model.ProductImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductImageRestMapper
{
    UploadProductImageDTO convertRequestToDTO(CreateProductImageRequest createProductImageRequest);

    ProductImage convertDTOtoResponse(ProductImageDTO productImageDTO);

    List<ProductImage> convertAllDTOstoResponse(List<ProductImageDTO> productImageDTOs);
}
