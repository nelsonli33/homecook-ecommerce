package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookstorefront.dto.ProductDTO;
import com.homecook.homecookstorefront.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {ProductImageRestMapper.class}
)
public interface ProductRestMapper
{
    Product convertDTOtoResponse(ProductDTO productDTO);

    List<Product> convertAllToResponse(List<ProductDTO> productDTOs);
}
