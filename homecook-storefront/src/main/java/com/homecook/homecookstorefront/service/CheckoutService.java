package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.*;

public interface CheckoutService
{
    CheckoutEntity getCheckout();

    void appliedShippingAddress(CheckoutEntity checkoutEntity, AddressEntity addressEntity);

    void appliedPaymentMethod(CheckoutEntity checkoutEntity, PaymentModeEntity paymentModeEntity);

    void appliedCheckoutInvoice(CheckoutEntity checkoutEntity, CheckoutInvoiceEntity checkoutInvoiceEntity);

    void removeCheckout(CheckoutEntity checkoutEntity);

    OrderEntity placeOrder(CheckoutEntity checkoutEntity);

    void validateCheckout(CheckoutEntity checkoutEntity);
}
