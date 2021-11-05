package com.homecook.homecookstorefront.dto;

import com.homecook.homecookentity.type.OrderStatusType;
import com.homecook.homecookentity.type.PaymentStatusType;

import java.util.Date;
import java.util.List;

public class OrderDTO
{
    private String orderCode;
    private OrderStatusType orderStatus;
    private Double subtotal;
    private Double totalDiscounts;
    private Double shippingCost;
    private Double totalPrice;
    private List<OrderLineItemDTO> lineItems;
    private PaymentModeDTO paymentMode;
    private PaymentStatusType paymentStatus;
    private Date payTime;
    private ShippingModeDTO shippingMode;
    private AddressDTO shippingAddress;
    private OrderInvoiceDTO invoice;
    private CustomerDTO customer;
    private String note;
    private Date shippedAt;
    private Date expShippingAt;
    private Date completeAt;
    private Date cancelledAt;
    private Date createdAt;
    private Date updatedAt;

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public OrderStatusType getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatusType orderStatus)
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

    public List<OrderLineItemDTO> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItemDTO> lineItems)
    {
        this.lineItems = lineItems;
    }

    public PaymentModeDTO getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeDTO paymentMode)
    {
        this.paymentMode = paymentMode;
    }

    public PaymentStatusType getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusType paymentStatus)
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

    public ShippingModeDTO getShippingMode()
    {
        return shippingMode;
    }

    public void setShippingMode(ShippingModeDTO shippingMode)
    {
        this.shippingMode = shippingMode;
    }

    public AddressDTO getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public OrderInvoiceDTO getInvoice()
    {
        return invoice;
    }

    public void setInvoice(OrderInvoiceDTO invoice)
    {
        this.invoice = invoice;
    }

    public CustomerDTO getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerDTO customer)
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
