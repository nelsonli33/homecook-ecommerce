package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.CartDTO;

public interface CartFacade
{
    CartDTO addToCart(Long productId, Long variantId, Integer quantity);
}
