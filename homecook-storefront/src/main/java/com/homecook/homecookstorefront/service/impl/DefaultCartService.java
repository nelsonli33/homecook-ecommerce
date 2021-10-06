package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CartLineItemEntity;
import com.homecook.homecookentity.repository.CartRepository;
import com.homecook.homecookstorefront.dto.SKUProduct;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CartFactory;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "cartService")
public class DefaultCartService implements CartService
{
    private CartRepository cartRepository;
    private CartFactory cartFactory;
    private CustomerService customerService;

    @Autowired
    public DefaultCartService(CartRepository cartRepository, CartFactory cartFactory, CustomerService customerService)
    {
        this.cartRepository = cartRepository;
        this.cartFactory = cartFactory;
        this.customerService = customerService;
    }

    @Override
    public CartEntity getCartForCurrentCustomer()
    {
        final CartEntity cart = cartRepository.findCartEntityByCustomer(customerService.getCurrentCustomer());
        if (cart != null)
        {
            return cart;
        }
        else
        {
            return cartFactory.createCart();
        }
    }

    @Override
    public CartLineItemEntity addLineItem(CartEntity cart, SKUProduct skuProduct, int qty)
    {

        validateParameterNotNullStandardMessage("cart", cart);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        if (qty <= 0)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INTERNAL_SERVER_ERROR, "Quantity must be a positive non-zero value");
        }

        CartLineItemEntity lineItem = getCartLineItemForSKUProduct(cart, skuProduct);

        if (lineItem != null)
        {
            lineItem.setQuantity(lineItem.getQuantity() + qty);
            return lineItem;
        }

        lineItem = new CartLineItemEntity();
        lineItem.setName(skuProduct.getName());
        lineItem.setQuantity(qty);
        lineItem.setPrice(skuProduct.getPrice());
        lineItem.setSku(skuProduct.getSku());
        lineItem.setProduct(skuProduct.getProduct());
        lineItem.setVariant(skuProduct.getVariant());
        lineItem.setCart(cart);
        cart.getLineItems().add(lineItem);
        return lineItem;
    }

    @Override
    public CartLineItemEntity getCartLineItemForSKUProduct(CartEntity cart, SKUProduct skuProduct)
    {
        validateParameterNotNullStandardMessage("cart", cart);
        validateParameterNotNullStandardMessage("skuProduct", skuProduct);

        final List<CartLineItemEntity> lineItems = cart.getLineItems();
        if (lineItems == null || lineItems.isEmpty() || skuProduct.getProduct() == null)
        {
            return null;
        }

        for (final CartLineItemEntity lineItem : lineItems)
        {
            if (skuProduct.getHasVariant() && lineItem.getProduct().equals(skuProduct.getProduct()) && lineItem.getVariant().equals(skuProduct.getVariant()))
            {
                return lineItem;
            }

            if (!skuProduct.getHasVariant() && lineItem.getProduct().equals(skuProduct.getProduct()))
            {
                return lineItem;
            }
        }
        return null;
    }
}
