package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.annotation.IgnoreResponseAdvice;
import com.homecook.homecookstorefront.controller.mapper.CheckoutRestMapper;
import com.homecook.homecookstorefront.controller.mapper.CustomerRestMapper;
import com.homecook.homecookstorefront.controller.mapper.OrderRestMapper;
import com.homecook.homecookstorefront.dto.AddressDTO;
import com.homecook.homecookstorefront.dto.CheckoutDTO;
import com.homecook.homecookstorefront.dto.CheckoutInvoiceDTO;
import com.homecook.homecookstorefront.dto.OrderDTO;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.facade.CartFacade;
import com.homecook.homecookstorefront.facade.CheckoutFacade;
import com.homecook.homecookstorefront.model.*;
import ecpay.logistics.integration.EcpayLogisticsClient;
import ecpay.logistics.integration.domain.ExpressMapObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckoutController
{
    private static final Logger log = LoggerFactory.getLogger(CheckoutController.class);

    private EcpayLogisticsClient ecpayLogisticsClient;
    private CheckoutFacade checkoutFacade;
    private CartFacade cartFacade;
    private CustomerRestMapper customerRestMapper;
    private CheckoutRestMapper checkoutRestMapper;
    private OrderRestMapper orderRestMapper;

    @Autowired
    public CheckoutController(
            EcpayLogisticsClient ecpayLogisticsClient,
            CheckoutFacade checkoutFacade,
            CartFacade cartFacade,
            CustomerRestMapper customerRestMapper,
            CheckoutRestMapper checkoutRestMapper,
            OrderRestMapper orderRestMapper)
    {
        this.ecpayLogisticsClient = ecpayLogisticsClient;
        this.checkoutFacade = checkoutFacade;
        this.cartFacade = cartFacade;
        this.customerRestMapper = customerRestMapper;
        this.checkoutRestMapper = checkoutRestMapper;
        this.orderRestMapper = orderRestMapper;
    }


    @GetMapping(value = "/api/v1/checkout")
    public ResponseEntity<Checkout> doCheckout()
    {
        beforeCheckout();

        cartFacade.validateCart();

        final CheckoutDTO checkoutData = checkoutFacade.getCheckout();

        final Checkout checkout = checkoutRestMapper.toCheckout(checkoutData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(checkout);
    }

    @PostMapping(value = "/api/v1/checkout/place-order")
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest)
    {
        // 1. validate place order request

        beforeCheckout();


        final CheckoutInvoiceDTO checkoutInvoiceDTO
                = checkoutRestMapper.toCheckoutInvoiceDTO(placeOrderRequest.getInvoice());
        checkoutFacade.appliedInvoice(checkoutInvoiceDTO);
        checkoutFacade.appliedPaymentMethod(placeOrderRequest.getPaymentMethodCode());

        // 2. validate cart info
        cartFacade.validateCart();

        // 3. validate checkout info
        checkoutFacade.validateCheckout();

        // 4. place order
        final OrderDTO orderDTO = checkoutFacade.placeOrder();

        final Order order = orderRestMapper.toOrder(orderDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(order);

    }

    @PostMapping(value = "/api/v1/checkout/applied-shipping-address")
    public ResponseEntity<Checkout> appliedShippingAddressForCheckout(@RequestBody AppliedShippingAddressRequest appliedShippingAddressRequest)
    {
        beforeCheckout();

        checkoutFacade.appliedShippingAddress(appliedShippingAddressRequest.getAddressId());

        final CheckoutDTO checkoutData = checkoutFacade.getCheckout();

        final Checkout checkout = checkoutRestMapper.toCheckout(checkoutData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(checkout);
    }

    @GetMapping(value = "/api/v1/checkout/shipping-addresses")
    public ResponseEntity<List<Address>> getShippingAddresses()
    {
        beforeCheckout();

        final List<AddressDTO> supportedShippingAddresses
                = checkoutFacade.getSupportedShippingAddresses();

        final List<Address> addresses
                = customerRestMapper.toCustomerAddresses(supportedShippingAddresses);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(addresses);
    }


    @IgnoreResponseAdvice
    @PostMapping(value = "/api/v1/checkout/shipping-express-map")
    public String cvsExpressMap(@RequestBody ExpressMapRequest expressMapRequest)
    {
        beforeCheckout();

        ExpressMapObj obj = new ExpressMapObj();
        obj.setLogisticsSubType("OKMARTC2C");
        obj.setIsCollection("N");
        obj.setServerReplyURL(expressMapRequest.getServerReplyUrl());
        return ecpayLogisticsClient.expressMap(obj);
    }

    private void beforeCheckout()
    {
        if (!checkoutFacade.hasValidCart())
        {
            log.info("Missing, empty cart");
            throw new StorefrontServerRuntimeException(InternalErrorCode.CART_CHECKOUT_ERROR, "Empty cart");
        }
    }

}
