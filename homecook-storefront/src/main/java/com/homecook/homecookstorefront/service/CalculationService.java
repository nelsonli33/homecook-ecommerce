package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CheckoutEntity;

public interface CalculationService
{
    void calculate(CartEntity cart);

    void calculate(CheckoutEntity checkout);
}
