package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.CartRestMapper;
import com.homecook.homecookstorefront.dto.CartDTO;
import com.homecook.homecookstorefront.facade.CartFacade;
import com.homecook.homecookstorefront.model.AddToCartRequest;
import com.homecook.homecookstorefront.model.Cart;
import com.homecook.homecookstorefront.model.ServerResponse;
import com.homecook.homecookstorefront.model.UpdateCartRequest;
import com.homecook.homecookstorefront.util.validator.CartReqMsgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController
{
    private CartFacade cartFacade;
    private CartRestMapper cartRestMapper;
    private CartReqMsgValidator cartReqMsgValidator;

    @Autowired
    public CartController(CartFacade cartFacade, CartRestMapper cartRestMapper, CartReqMsgValidator cartReqMsgValidator)
    {
        this.cartFacade = cartFacade;
        this.cartRestMapper = cartRestMapper;
        this.cartReqMsgValidator = cartReqMsgValidator;
    }


    @GetMapping(value = "/api/v1/cart")
    public ResponseEntity<Cart> getCartDetails()
    {
        final CartDTO cartDTO = cartFacade.getCartForCurrentCustomer();
        final Cart cart = cartRestMapper.convertDTOtoResponse(cartDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    @PostMapping(value = "/api/v1/cart/add")
    public ResponseEntity<Cart> addToCart(@RequestBody final AddToCartRequest addToCartRequest, final BindingResult bindingResult)
    {
        cartReqMsgValidator.validateAddToCartRequest(addToCartRequest, bindingResult);

        final CartDTO cartDTO = cartFacade.addToCart(addToCartRequest.getProductId(), addToCartRequest.getVariantId(), addToCartRequest.getQty());
        final Cart cart = cartRestMapper.convertDTOtoResponse(cartDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


    @PutMapping(value = "/api/v1/cart/update")
    public ResponseEntity<Cart> updateCartLineItem(@RequestBody final UpdateCartRequest updateCartRequest, final BindingResult bindingResult)
    {
        cartReqMsgValidator.validateUpdateCartRequest(updateCartRequest, bindingResult);

        final CartDTO cartDTO = cartFacade.updateCartLineItem(updateCartRequest.getProductId(), updateCartRequest.getVariantId(), updateCartRequest.getQty());
        final Cart cart = cartRestMapper.convertDTOtoResponse(cartDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }


    @PostMapping(value = "/api/v1/cart/clear")
    public ResponseEntity<Cart> clearCart()
    {

        final CartDTO cartDTO = cartFacade.clearCart();
        final Cart cart = cartRestMapper.convertDTOtoResponse(cartDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cart);
    }

    @PostMapping(value = "/api/v1/cart/checkout")
    public ResponseEntity<ServerResponse> cartCheckout()
    {
        cartFacade.validateCart();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ServerResponse.success());
    }
}
