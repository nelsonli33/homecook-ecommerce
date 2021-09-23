package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.PageDTO;
import com.homecook.homecookadmin.dto.ProductDTO;
import com.homecook.homecookadmin.dto.ProductSpecDTO;
import com.homecook.homecookadmin.dto.ProductSpecValueDTO;
import com.homecook.homecookadmin.model.*;
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
            @Mapping(source = "specItems", target = "specs", qualifiedByName = "convertSpecItemsToProductSpecDTOs"),
            @Mapping(target = "variants.name", ignore = true),
            @Mapping(target = "variants.specValue1Id", ignore = true),
            @Mapping(target = "variants.specValue2Id", ignore = true),
            @Mapping(target = "variants.specValue3Id", ignore = true),
    })
    ProductDTO convertRequestToDTO(CreateProductRequest createProductRequest);

    @Mappings({
            @Mapping(source = "specs", target = "specs", qualifiedByName = "convertSpecsToProductSpecDTOs"),
            @Mapping(target = "variants.name", ignore = true),
    })
    ProductDTO convertRequestToDTO(UpdateProductRequest updateProductRequest);


    Product convertDTOtoResponse(ProductDTO productDTO);

    @Mapping(source = "result", target = "products")
    ProductPaginationResult convertPageDTOtoResponse(PageDTO<ProductDTO> productDTO);

    List<Product> convertAllToResponse(List<ProductDTO> productDTOs);


    @Named("convertSpecsToProductSpecDTOs")
    default List<ProductSpecDTO> convertSpecsToProductSpecDTOs(List<ProductSpec> specs)
    {
        if (CollectionUtils.isNotEmpty(specs))
        {
            List<ProductSpecDTO> result = new ArrayList<>();

            for (ProductSpec productSpec : specs)
            {
                ProductSpecDTO dto = new ProductSpecDTO();
                if (productSpec.getId() != null) {
                    dto.setId(productSpec.getId());
                }
                if (productSpec.getName() != null) {
                    dto.setName(productSpec.getName());
                }


                final List<ProductSpecValue> productSpecValues = productSpec.getValues();
                if (CollectionUtils.isNotEmpty(productSpecValues)) {
                    List<ProductSpecValueDTO> specValueDTOS = productSpecValues.stream().map(specValue -> {
                        ProductSpecValueDTO valueDTO = new ProductSpecValueDTO();
                        if (specValue.getId() != null) {
                            valueDTO.setId(specValue.getId());
                        }
                        if (specValue.getValue() != null) {
                            valueDTO.setValue(specValue.getValue());
                        }
                        return valueDTO;
                    }).collect(Collectors.toList());

                    dto.setValues(specValueDTOS);
                }
                result.add(dto);
            }
            return result;
        }
        return Collections.emptyList();
    }

    @Named("convertSpecItemsToProductSpecDTOs")
    default List<ProductSpecDTO> convertSpecItemsToProductSpecDTOs(List<SpecItem> specItems)
    {
        if (CollectionUtils.isNotEmpty(specItems))
        {
            List<ProductSpecDTO> result = new ArrayList<>();

            for (SpecItem specItem : specItems)
            {
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
