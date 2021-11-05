package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlaceOrderRequest
{
    @JsonProperty("checkoutCode")
    private String checkoutCode;

    @JsonProperty("paymentMethodCode")
    private String paymentMethodCode;

    @JsonProperty("invoice")
    private CheckoutInvoice invoice;

    public String getCheckoutCode()
    {
        return checkoutCode;
    }

    public void setCheckoutCode(String checkoutCode)
    {
        this.checkoutCode = checkoutCode;
    }

    public String getPaymentMethodCode()
    {
        return paymentMethodCode;
    }

    public void setPaymentMethodCode(String paymentMethodCode)
    {
        this.paymentMethodCode = paymentMethodCode;
    }

    public CheckoutInvoice getInvoice()
    {
        return invoice;
    }

    public void setInvoice(CheckoutInvoice invoice)
    {
        this.invoice = invoice;
    }
}
