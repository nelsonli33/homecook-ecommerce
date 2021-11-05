package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.service.CommerceCartService;
import com.homecook.homecookstorefront.service.strategy.AddToCartStrategy;
import com.homecook.homecookstorefront.service.strategy.CartValidationStrategy;
import com.homecook.homecookstorefront.service.strategy.RemoveLineItemsStrategy;
import com.homecook.homecookstorefront.service.strategy.UpdateCartLineItemStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "commerceCartService")
public class DefaultCommerceCartService implements CommerceCartService
{
    private AddToCartStrategy addToCartStrategy;
    private UpdateCartLineItemStrategy updateCartLineItemStrategy;
    private RemoveLineItemsStrategy removeLineItemsStrategy;
    private CartValidationStrategy cartValidationStrategy;

    @Autowired
    public DefaultCommerceCartService(
            AddToCartStrategy addToCartStrategy,
            UpdateCartLineItemStrategy updateCartLineItemStrategy,
            RemoveLineItemsStrategy removeLineItemsStrategy,
            CartValidationStrategy cartValidationStrategy)
    {
        this.addToCartStrategy = addToCartStrategy;
        this.updateCartLineItemStrategy = updateCartLineItemStrategy;
        this.removeLineItemsStrategy = removeLineItemsStrategy;
        this.cartValidationStrategy = cartValidationStrategy;
    }

    @Override
    public CartEntity addToCart(final CommerceCartParameter parameter)
    {
        return addToCartStrategy.addToCart(parameter);
    }

    @Override
    public CartEntity updateQuantityForCartLineItem(final CommerceCartParameter parameter)
    {
        return updateCartLineItemStrategy.updateQuantityForCartLineItem(parameter);
    }

    @Override
    public CartEntity removeAllLineItems(final CommerceCartParameter parameter)
    {
        return removeLineItemsStrategy.removeAllLineItems(parameter);
    }

    @Override
    public void validateCart(CommerceCartParameter parameter)
    {
        cartValidationStrategy.validateCart(parameter);
    }
}
