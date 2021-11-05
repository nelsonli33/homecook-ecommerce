package com.homecook.homecookstorefront.service.strategy;

import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface CartValidationStrategy
{
    void validateCart(final CommerceCartParameter parameter);
}
