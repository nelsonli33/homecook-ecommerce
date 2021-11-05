package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Checkout
{

    @JsonProperty("code")
    private String code;

    @JsonProperty("cart")
    private Cart cart;

    @JsonProperty("subtotal")
    private double subtotal;

    @JsonProperty("shippingCost")
    private double shippingCost;

    @JsonProperty("totalPrice")
    private double totalPrice;

    @JsonProperty("selectedPaymentMethod")
    private PaymentMode selectedPaymentMethod;

    @JsonProperty("paymentMethods")
    private List<PaymentMode> paymentMethods;

    @JsonProperty("invoiceSetting")
    private CustomerInvoiceSetting invoiceSetting;

    @JsonProperty("shippingAddress")
    private Address shippingAddress;

    @JsonProperty("shippingMethods")
    private List<ShippingMode> shippingMethods;


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(double subtotal)
    {
        this.subtotal = subtotal;
    }

    public double getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost)
    {
        this.shippingCost = shippingCost;
    }

    public double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public PaymentMode getSelectedPaymentMethod()
    {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(PaymentMode selectedPaymentMethod)
    {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    public List<PaymentMode> getPaymentMethods()
    {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMode> paymentMethods)
    {
        this.paymentMethods = paymentMethods;
    }

    public CustomerInvoiceSetting getInvoiceSetting()
    {
        return invoiceSetting;
    }

    public void setInvoiceSetting(CustomerInvoiceSetting invoiceSetting)
    {
        this.invoiceSetting = invoiceSetting;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public List<ShippingMode> getShippingMethods()
    {
        return shippingMethods;
    }

    public void setShippingMethods(List<ShippingMode> shippingMethods)
    {
        this.shippingMethods = shippingMethods;
    }
}