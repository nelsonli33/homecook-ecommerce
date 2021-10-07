package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.CartDTO;

public interface CartFacade
{
    CartDTO getCartForCurrentCustomer();

    CartDTO addToCart(Long productId, Long variantId, Integer quantity);

    CartDTO updateCartLineItem(Long productId, Long variantId, int quantity);

    CartDTO clearCart();
}
