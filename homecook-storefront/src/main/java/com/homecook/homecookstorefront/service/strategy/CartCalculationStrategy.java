package com.homecook.homecookstorefront.service.strategy;

import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface CartCalculationStrategy
{
    void calculateCart(CommerceCartParameter parameter);
}
