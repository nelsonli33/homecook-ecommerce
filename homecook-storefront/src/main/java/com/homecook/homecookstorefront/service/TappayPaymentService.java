package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.OrderEntity;
import com.homecook.homecookentity.entity.PaymentTransactionEntity;

public interface TappayPaymentService
{
    String getPaymentUrlForCreditCard(String prime, OrderEntity order);

    PaymentTransactionEntity authorizePayment(String gatewayTransactionId);

    void validatePaymentIsValid(OrderEntity order);
}
