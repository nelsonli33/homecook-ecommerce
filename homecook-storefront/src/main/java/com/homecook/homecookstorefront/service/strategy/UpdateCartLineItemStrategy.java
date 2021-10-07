package com.homecook.homecookstorefront.service.strategy;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface UpdateCartLineItemStrategy
{
    CartEntity updateQuantityForCartLineItem(CommerceCartParameter parameter);
}
