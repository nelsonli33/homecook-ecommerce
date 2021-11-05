package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Order
{
    @JsonProperty("orderCode")
    private String orderCode;

    @JsonProperty("orderStatus")
    private String orderStatus;

    @JsonProperty("subtotal")
    private Double subtotal;

    @JsonProperty("totalDiscounts")
    private Double totalDiscounts;

    @JsonProperty("shippingCost")
    private Double shippingCost;

    @JsonProperty("totalPrice")
    private Double totalPrice;

    @JsonProperty("lineItems")
    private List<OrderLineItem> lineItems;

    @JsonProperty("paymentMode")
    private PaymentMode paymentMode;

    @JsonProperty("paymentStatus")
    private String paymentStatus;

    @JsonProperty("payTime")
    private Date payTime;

    @JsonProperty("shippingMode")
    private ShippingMode shippingMode;

    @JsonProperty("shippingAddress")
    private Address shippingAddress;

    @JsonProperty("invoice")
    private OrderInvoice invoice;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("note")
    private String note;

    @JsonProperty("shippedAt")
    private Date shippedAt;

    @JsonProperty("expShippingAt")
    private Date expShippingAt;

    @JsonProperty("completeAt")
    private Date completeAt;

    @JsonProperty("cancelledAt")
    private Date cancelledAt;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedAt")
    private Date updatedAt;

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Double getSubtotal()
    {
        return subtotal;
    }

    public void setSubtotal(Double subtotal)
    {
        this.subtotal = subtotal;
    }

    public Double getTotalDiscounts()
    {
        return totalDiscounts;
    }

    public void setTotalDiscounts(Double totalDiscounts)
    {
        this.totalDiscounts = totalDiscounts;
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

    public List<OrderLineItem> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems)
    {
        this.lineItems = lineItems;
    }

    public PaymentMode getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public Date getPayTime()
    {
        return payTime;
    }

    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public ShippingMode getShippingMode()
    {
        return shippingMode;
    }

    public void setShippingMode(ShippingMode shippingMode)
    {
        this.shippingMode = shippingMode;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public OrderInvoice getInvoice()
    {
        return invoice;
    }

    public void setInvoice(OrderInvoice invoice)
    {
        this.invoice = invoice;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Date getShippedAt()
    {
        return shippedAt;
    }

    public void setShippedAt(Date shippedAt)
    {
        this.shippedAt = shippedAt;
    }

    public Date getExpShippingAt()
    {
        return expShippingAt;
    }

    public void setExpShippingAt(Date expShippingAt)
    {
        this.expShippingAt = expShippingAt;
    }

    public Date getCompleteAt()
    {
        return completeAt;
    }

    public void setCompleteAt(Date completeAt)
    {
        this.completeAt = completeAt;
    }

    public Date getCancelledAt()
    {
        return cancelledAt;
    }

    public void setCancelledAt(Date cancelledAt)
    {
        this.cancelledAt = cancelledAt;
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
