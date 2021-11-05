package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;

@Table(name = EntityConstant.Table.Checkout)
@Entity
public class CheckoutEntity extends AbstractBaseEntity
{
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "note")
    private String note;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    // 商品總金額
    @Column(name = "subtotal")
    private Double subtotal;

    // 運費
    @Column(name = "shipping_cost")
    private Double shippingCost;

    // 訂單總金額
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private AddressEntity shippingAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_mode_id")
    private PaymentModeEntity paymentMode;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "checkout_invoice_id")
    private CheckoutInvoiceEntity checkoutInvoice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public CartEntity getCart()
    {
        return cart;
    }

    public void setCart(CartEntity cart)
    {
        this.cart = cart;
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

    public AddressEntity getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(AddressEntity shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public PaymentModeEntity getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEntity paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }

    public CheckoutInvoiceEntity getCheckoutInvoice()
    {
        return checkoutInvoice;
    }

    public void setCheckoutInvoice(CheckoutInvoiceEntity checkoutInvoice)
    {
        this.checkoutInvoice = checkoutInvoice;
    }
}
