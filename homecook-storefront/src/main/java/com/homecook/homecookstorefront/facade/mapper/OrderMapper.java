package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.OrderInvoiceEntity;
import com.homecook.homecookentity.entity.OrderLineItemEntity;
import com.homecook.homecookstorefront.dto.OrderDTO;
import com.homecook.homecookstorefront.dto.OrderInvoiceDTO;
import com.homecook.homecookstorefront.dto.OrderLineItemDTO;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AddressMapper.class, PaymentModeMapper.class, ShippingModeMapper.class, CustomerMapper.class}
)
public abstract class OrderMapper
{
    @Mappings({
            @Mapping(source = "code", target = "orderCode")
    })
    public abstract OrderDTO convertToOrderDTO(OrderEntity orderEntity);

    public abstract OrderLineItemDTO convertToOrderLineItemDTO(OrderLineItemEntity orderLineItemEntity);

    public abstract OrderInvoiceDTO convertToOrderInvoiceDTO(OrderInvoiceEntity orderInvoiceEntity);
}
