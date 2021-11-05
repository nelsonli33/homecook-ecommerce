package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.facade.PaymentFacade;
import com.homecook.homecookstorefront.model.PayOrderRequest;
import com.homecook.tappay.models.BackendNotifyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderPaymentController
{
    @Resource(name = "paymentFacade")
    private PaymentFacade paymentFacade;

    @PostMapping(value = "/api/v1/order/{orderCode}/pay")
    public ResponseEntity<String> payOrder(@PathVariable(value = "orderCode") String orderCode,
                                           @RequestBody PayOrderRequest payOrderRequest)
    {
        final String paymentUrl = paymentFacade.beginPayment(payOrderRequest.getPrime(), orderCode);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentUrl);
    }

    @PostMapping(value = "/api/v1/order/payment/result")
    public ResponseEntity<?> orderPaymentResultCallback(@RequestBody BackendNotifyRequest request)
    {
        if (request.getStatus() == 0)
        {
            paymentFacade.authorizePayment(request);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }
}
