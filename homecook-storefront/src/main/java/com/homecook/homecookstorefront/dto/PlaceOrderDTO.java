package com.homecook.homecookstorefront.dto;

public class PlaceOrderDTO
{
    private String checkoutCode;
    private String paymentMethodCode;
    private CheckoutInvoiceDTO invoice;

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

    public CheckoutInvoiceDTO getInvoice()
    {
        return invoice;
    }

    public void setInvoice(CheckoutInvoiceDTO invoice)
    {
        this.invoice = invoice;
    }
}
