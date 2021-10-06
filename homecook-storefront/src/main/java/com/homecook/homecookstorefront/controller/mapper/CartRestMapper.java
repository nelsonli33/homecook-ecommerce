package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.dto.CartLineItemDTO;
import com.homecook.homecookstorefront.model.Cart;
import com.homecook.homecookstorefront.model.CartLineItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {AccountRestMapper.class}
)
public interface CartRestMapper
{
    Cart convertDTOtoResponse(CartDTO cartDTO);

    CartLineItem convertDTOtoResponse(CartLineItemDTO cartLineItemDTO);
}
