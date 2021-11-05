package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.*;

import java.util.List;

public interface CheckoutFacade
{
    CheckoutDTO getCheckout();

    void appliedPaymentMethod(String paymentMethodCode);

    void appliedShippingAddress(Long addressId);

    void appliedInvoice(CheckoutInvoiceDTO checkoutInvoiceDTO);

    OrderDTO placeOrder();

    List<PaymentModeDTO> getSupportedPaymentMethods();

    List<ShippingModeDTO> getSupportedShippingMethods();

    List<AddressDTO> getSupportedShippingAddresses();

    boolean hasValidCart();

    void validateCheckout();
}
