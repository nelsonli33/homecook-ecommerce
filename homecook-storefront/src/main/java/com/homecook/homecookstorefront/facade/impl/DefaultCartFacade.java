package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookstorefront.dto.AddToCartDTO;
import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.dto.CommerceCartParameter;
import com.homecook.homecookstorefront.facade.CartFacade;
import com.homecook.homecookstorefront.facade.mapper.CartMapper;
import com.homecook.homecookstorefront.facade.mapper.CommerceCartMapper;
import com.homecook.homecookstorefront.service.CommerceCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cartFacade")
public class DefaultCartFacade implements CartFacade
{
    private CommerceCartService commerceCartService;
    private CommerceCartMapper commerceCartMapper;
    private CartMapper cartMapper;

    @Autowired
    public DefaultCartFacade(CommerceCartService commerceCartService, CommerceCartMapper commerceCartMapper, CartMapper cartMapper)
    {
        this.commerceCartService = commerceCartService;
        this.commerceCartMapper = commerceCartMapper;
        this.cartMapper = cartMapper;
    }

    @Override
    public CartDTO addToCart(Long productId, Long variantId, Integer quantity)
    {
        AddToCartDTO dto = new AddToCartDTO();
        dto.setProductId(productId);
        dto.setVariantId(variantId);
        dto.setQuantity(quantity);
        return addToCart(dto);
    }

    protected CartDTO addToCart(AddToCartDTO addToCartDTO)
    {
        final CommerceCartParameter commerceCartParameter = commerceCartMapper.convertToCommerceCartParameter(addToCartDTO);
        final CartEntity cartEntity = commerceCartService.addToCart(commerceCartParameter);
        return cartMapper.convertToCartDTO(cartEntity);
    }
}
