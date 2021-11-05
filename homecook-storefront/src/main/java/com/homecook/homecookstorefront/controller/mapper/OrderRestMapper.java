package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookentity.type.OrderStatusType;
import com.homecook.homecookentity.type.PaymentStatusType;
import com.homecook.homecookstorefront.dto.AddressDTO;
import com.homecook.homecookstorefront.dto.OrderDTO;
import com.homecook.homecookstorefront.dto.OrderLineItemDTO;
import com.homecook.homecookstorefront.model.Address;
import com.homecook.homecookstorefront.model.Order;
import com.homecook.homecookstorefront.model.OrderLineItem;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {CheckoutRestMapper.class}
)
public interface OrderRestMapper
{
    @Mappings({
            @Mapping(source = "orderStatus", target = "orderStatus", qualifiedByName = "orderStatusConverter"),
            @Mapping(source = "paymentStatus", target = "paymentStatus", qualifiedByName = "paymentStatusConverter")
    })
    Order toOrder(OrderDTO orderDTO);

    OrderLineItem toOrderLineItem(OrderLineItemDTO orderLineItemDTO);

    @Mappings({
            @Mapping(source = "shippingMode.code", target = "code"),
            @Mapping(source = "shippingMode.name", target = "type"),
    })
    Address toAddress(AddressDTO addressDTO);

    @Named("orderStatusConverter")
    default String orderStatusConverter(OrderStatusType orderStatus)
    {
        return orderStatus.getCode();
    }

    @Named("paymentStatusConverter")
    default String paymentStatusConverter(PaymentStatusType paymentStatus)
    {
        return paymentStatus.getCode();
    }


}
