package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.dto.CartLineItemDTO;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {CustomerMapper.class}
)
public abstract class CartMapper
{
    @Mappings(
            @Mapping(source = "lineItems", target = "items")
    )
    public abstract CartDTO convertToCartDTO(CartEntity cartEntity);

    public abstract CartLineItemDTO convertToCartLineItemDTO(CartLineItemEntity cartLineItemEntity);
}
