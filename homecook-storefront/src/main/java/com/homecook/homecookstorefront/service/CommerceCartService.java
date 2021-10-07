package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface CommerceCartService
{
    CartEntity addToCart(CommerceCartParameter parameter);

    CartEntity updateQuantityForCartLineItem(CommerceCartParameter parameter);

    CartEntity removeAllLineItems(final CommerceCartParameter parameter);
}
