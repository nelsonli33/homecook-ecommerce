package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface CommerceCartService
{
    CartEntity addToCart(CommerceCartParameter parameter);
}
