package com.homecook.homecookstorefront.dto;

import java.util.Date;
import java.util.List;

public class CheckoutDTO
{
    private Long id;
    private String code;
    private Double subtotal;
    private Double shippingCost;
    private Double totalPrice;
    private CartDTO cart;
    private AddressDTO shippingAddress;
    private PaymentModeDTO selectedPaymentMethod;
    private List<PaymentModeDTO> paymentMethods;
    private List<ShippingModeDTO> shippingMethods;
    private InvoiceSettingDTO invoiceSetting;
    private Date createdAt;
    private Date updatedAt;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost)
    {
        this.shippingCost = shippingCost;
    }

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public CartDTO getCart()
    {
        return cart;
    }

    public void setCart(CartDTO cart)
    {
        this.cart = cart;
    }

    public AddressDTO getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public PaymentModeDTO getSelectedPaymentMethod()
    {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(PaymentModeDTO selectedPaymentMethod)
    {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }

    public List<PaymentModeDTO> getPaymentMethods()
    {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentModeDTO> paymentMethods)
    {
        this.paymentMethods = paymentMethods;
    }

    public List<ShippingModeDTO> getShippingMethods()
    {
        return shippingMethods;
    }

    public void setShippingMethods(List<ShippingModeDTO> shippingMethods)
    {
        this.shippingMethods = shippingMethods;
    }

    public InvoiceSettingDTO getInvoiceSetting()
    {
        return invoiceSetting;
    }

    public void setInvoiceSetting(InvoiceSettingDTO invoiceSetting)
    {
        this.invoiceSetting = invoiceSetting;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }
}
