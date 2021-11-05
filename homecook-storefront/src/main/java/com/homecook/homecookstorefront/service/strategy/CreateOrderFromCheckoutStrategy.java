package com.homecook.homecookstorefront.service.strategy;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.OrderEntity;

public interface CreateOrderFromCheckoutStrategy
{
    OrderEntity createOrderFromCheckout(CheckoutEntity checkout);
}
