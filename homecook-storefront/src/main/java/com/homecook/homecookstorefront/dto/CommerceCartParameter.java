package com.homecook.homecookstorefront.dto;

import com.homecook.homecookentity.entity.CartEntity;

public class CommerceCartParameter
{
    private CartEntity cart;
    private SKUProduct skuProduct;
    private Integer quantity;


    public CartEntity getCart()
    {
        return cart;
    }

    public void setCart(CartEntity cart)
    {
        this.cart = cart;
    }

    public SKUProduct getSkuProduct()
    {
        return skuProduct;
    }

    public void setSkuProduct(SKUProduct skuProduct)
    {
        this.skuProduct = skuProduct;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Integer quantity)
    {
        this.quantity = quantity;
    }
}
