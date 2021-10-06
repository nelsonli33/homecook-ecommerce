package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.CartRestMapper;
import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.facade.CartFacade;
import com.homecook.homecookstorefront.model.AddToCartRequest;
import com.homecook.homecookstorefront.model.Cart;
import com.homecook.homecookstorefront.util.validator.CartReqMsgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController
{
    private CartFacade cartFacade;
    private CartRestMapper cartRestMapper;

    @Autowired
    public CartController(CartFacade cartFacade, CartRestMapper cartRestMapper)
    {
        this.cartFacade = cartFacade;
        this.cartRestMapper = cartRestMapper;
    }

    @PostMapping(value = "/api/v1/cart/add")
    public ResponseEntity<Cart> addToCart(@RequestBody final AddToCartRequest addToCartRequest, final BindingResult bindingResult)
    {
        CartReqMsgValidator.validateAddToCartRequest(addToCartRequest, bindingResult);

        final CartDTO cartDTO = cartFacade.addToCart(addToCartRequest.getProductId(), addToCartRequest.getVariantId(), addToCartRequest.getQty());
        final Cart cart = cartRestMapper.convertDTOtoResponse(cartDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }
}
