package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;

public interface CommerceCartService
{
    CartEntity addToCart(final CommerceCartParameter parameter);

    CartEntity updateQuantityForCartLineItem(final CommerceCartParameter parameter);

    CartEntity removeAllLineItems(final CommerceCartParameter parameter);

    void validateCart(final CommerceCartParameter parameter);
}
