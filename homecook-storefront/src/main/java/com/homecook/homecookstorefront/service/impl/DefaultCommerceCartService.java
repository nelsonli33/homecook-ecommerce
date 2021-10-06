package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.service.CommerceCartService;
import com.homecook.homecookstorefront.service.strategy.AddToCartStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "commerceCartService")
public class DefaultCommerceCartService implements CommerceCartService
{
    private AddToCartStrategy addToCartStrategy;


    @Autowired
    public DefaultCommerceCartService(AddToCartStrategy addToCartStrategy)
    {
        this.addToCartStrategy = addToCartStrategy;
    }

    @Override
    public CartEntity addToCart(CommerceCartParameter parameter)
    {
        return addToCartStrategy.addToCart(parameter);
    }
}
