package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.OrderEntity;

public interface OrderService
{
    OrderEntity getOrderForCode(String orderCode);

    OrderEntity getOrderForCodeNoAuth(String orderCode);

    OrderEntity createOrderFromCheckout(CheckoutEntity checkout);
}
