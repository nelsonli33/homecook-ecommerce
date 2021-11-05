package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookstorefront.dto.SKUProduct;

public interface CartService
{
    CartEntity getCartForCurrentCustomer();

    CartEntity refreshCart(CartEntity cart);

    CartLineItemEntity addLineItem(CartEntity cart, SKUProduct skuProduct, int qty);

    CartLineItemEntity getCartLineItemForSKUProduct(CartEntity cart, SKUProduct skuProduct);

    void removeCart();
}
