package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.OrderRestMapper;
import com.homecook.homecookstorefront.dto.OrderDTO;
import com.homecook.homecookstorefront.facade.OrderFacade;
import com.homecook.homecookstorefront.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController
{
    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;

    @Autowired
    private OrderRestMapper orderRestMapper;

    @GetMapping(value = "/api/v1/order/{orderCode}")
    public ResponseEntity<Order> getOrderDetail(@PathVariable(value = "orderCode") String orderCode)
    {

        final OrderDTO orderDetail = orderFacade.getOrderDetail(orderCode);

        final Order order = orderRestMapper.toOrder(orderDetail);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }

}
