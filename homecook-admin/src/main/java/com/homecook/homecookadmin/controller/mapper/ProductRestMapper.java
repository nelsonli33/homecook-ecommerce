package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.dto.ProductSpecValueDTO;
import com.homecook.homecookadmin.model.CreateProductRequest;
import com.homecook.homecookadmin.model.Product;
import com.homecook.homecookadmin.model.SpecItem;
import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface ProductRestMapper
{

    @Mappings({
            @Mapping(source = "specItems", target = "specs", qualifiedByName = "convertAllToProductSpecDTO")
    })
    ProductDTO convertRequestToDTO(CreateProductRequest createProductRequest);

    Product convertDTOtoResponse(ProductDTO productDTO);


    @Named("convertAllToProductSpecDTO")
    default List<ProductSpecDTO> convertAllToProductSpecDTO(List<SpecItem> specItems)
    {
        if (CollectionUtils.isNotEmpty(specItems)) {
            List<ProductSpecDTO> result = new ArrayList<>();

            for(SpecItem specItem : specItems) {
                ProductSpecDTO dto = new ProductSpecDTO();
                dto.setName(specItem.getName());

                List<ProductSpecValueDTO> specValueDTOS = specItem.getValues().stream().map(value -> {
                    ProductSpecValueDTO valueDTO = new ProductSpecValueDTO();
                    valueDTO.setValue(value);
                    return valueDTO;
                }).collect(Collectors.toList());

                dto.setValues(specValueDTOS);
                result.add(dto);
            }
            return result;
        }
        return Collections.emptyList();
    }
}
