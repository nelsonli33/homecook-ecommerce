package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.model.ProductSpec;
import com.homecook.homecookadmin.model.UpdateProductSpecRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductSpecRestMapper
{
    ProductSpecDTO convertRequestToDTO(UpdateProductSpecRequest updateProductSpecRequest);

    ProductSpec convertDTOtoResponse(ProductSpecDTO productSpecDTO);

    List<ProductSpec> convertAllDTOtoResponse(List<ProductSpecDTO> productSpecDTOs);
}
