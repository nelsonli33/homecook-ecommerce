package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.AddToCartDTO;
import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.dto.UpdateCartDTO;
import com.homecook.homecookstorefront.facade.CartFacade;
import com.homecook.homecookstorefront.facade.mapper.CartMapper;
import com.homecook.homecookstorefront.facade.mapper.CommerceCartMapper;
import com.homecook.homecookstorefront.service.CartService;
import com.homecook.homecookstorefront.service.CommerceCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cartFacade")
public class DefaultCartFacade implements CartFacade
{
    private CommerceCartService commerceCartService;
    private CartService cartService;
    private CommerceCartMapper commerceCartMapper;
    private CartMapper cartMapper;

    @Autowired
    public DefaultCartFacade(CommerceCartService commerceCartService, CartService cartService, CommerceCartMapper commerceCartMapper, CartMapper cartMapper)
    {
        this.commerceCartService = commerceCartService;
        this.cartService = cartService;
        this.commerceCartMapper = commerceCartMapper;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartDTO getCartForCurrentCustomer()
    {
        final CartEntity cartEntity = cartService.getCartForCurrentCustomer();
        cartService.refreshCart(cartEntity);
        return cartMapper.convertToCartDTO(cartEntity);
    }

    @Override
    public CartDTO addToCart(Long productId, Long variantId, Integer quantity)
    {
        AddToCartDTO dto = new AddToCartDTO();
        dto.setProductId(productId);
        dto.setVariantId(variantId);
        dto.setQuantity(quantity);

        final CommerceCartParameter commerceCartParameter = commerceCartMapper.convertToCommerceCartParameter(dto);
        final CartEntity cartEntity = commerceCartService.addToCart(commerceCartParameter);
        return cartMapper.convertToCartDTO(cartEntity);
    }


    @Override
    public CartDTO updateCartLineItem(Long productId, Long variantId, int quantity)
    {
        UpdateCartDTO dto = new UpdateCartDTO();
        dto.setProductId(productId);
        dto.setVariantId(variantId);
        dto.setQuantity(quantity);

        final CommerceCartParameter commerceCartParameter = commerceCartMapper.convertToCommerceCartParameter(dto);
        final CartEntity cartEntity = commerceCartService.updateQuantityForCartLineItem(commerceCartParameter);
        return cartMapper.convertToCartDTO(cartEntity);
    }

    @Override
    public CartDTO clearCart()
    {
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setCart(cartService.getCartForCurrentCustomer());

        final CartEntity cartEntity = commerceCartService.removeAllLineItems(parameter);
        return cartMapper.convertToCartDTO(cartEntity);
    }

    @Override
    public void validateCart()
    {
        final CommerceCartParameter parameter = new CommerceCartParameter();
        parameter.setCart(cartService.getCartForCurrentCustomer());

        commerceCartService.validateCart(parameter);
    }
}
