package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookstorefront.dto.OrderDTO;
import com.homecook.homecookstorefront.facade.OrderFacade;
import com.homecook.homecookstorefront.facade.mapper.OrderMapper;
import com.homecook.homecookstorefront.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "orderFacade")
public class DefaultOrderFacade implements OrderFacade
{
    private OrderService orderService;
    private OrderMapper orderMapper;

    @Autowired
    public DefaultOrderFacade(OrderService orderService, OrderMapper orderMapper)
    {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDTO getOrderDetail(String orderCode)
    {
        final OrderEntity order = getOrderService().getOrderForCode(orderCode);
        return getOrderMapper().convertToOrderDTO(order);
    }

    public OrderService getOrderService()
    {
        return orderService;
    }

    public void setOrderService(OrderService orderService)
    {
        this.orderService = orderService;
    }

    public OrderMapper getOrderMapper()
    {
        return orderMapper;
    }

    public void setOrderMapper(OrderMapper orderMapper)
    {
        this.orderMapper = orderMapper;
    }
}
