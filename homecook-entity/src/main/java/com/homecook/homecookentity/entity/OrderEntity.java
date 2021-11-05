package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.OrderStatusType;
import com.homecook.homecookentity.type.PaymentStatusType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = EntityConstant.Table.Order, indexes = {
        @Index(name = "idx_orderentity_code", columnList = "code")
})
@Entity
public class OrderEntity extends AbstractBaseEntity
{
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "order_status")
    private OrderStatusType orderStatus;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "total_discounts")
    private Double totalDiscounts;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    // 訂單總金額(實際支付的金額)
    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_mode_id")
    private PaymentModeEntity paymentMode;

    @Column(name = "payment_status")
    private PaymentStatusType paymentStatus;

    @Column(name = "pay_time")
    private Date payTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_mode_id")
    private ShippingModeEntity shippingMode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddressEntity shippingAddress;

    @Column(name = "note")
    private String note;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private OrderInvoiceEntity invoice;

    @Column(name = "shipped_at")
    private Date shippedAt;

    @Column(name = "exp_shipping_at")
    private Date expShippingAt;

    @Column(name = "complete_at")
    private Date completeAt;

    @Column(name = "cancelled_at")
    private Date cancelledAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderLineItemEntity> lineItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<PaymentTransactionEntity> transactions;


    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }


    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
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

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public PaymentModeEntity getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode(PaymentModeEntity paymentMode)
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

    public Double getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost)
    {
        this.shippingCost = shippingCost;
    }

    public ShippingModeEntity getShippingMode()
    {
        return shippingMode;
    }

    public void setShippingMode(ShippingModeEntity shippingMode)
    {
        this.shippingMode = shippingMode;
    }

    public ShippingAddressEntity getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressEntity shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public OrderInvoiceEntity getInvoice()
    {
        return invoice;
    }

    public void setInvoice(OrderInvoiceEntity invoice)
    {
        this.invoice = invoice;
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

    public List<OrderLineItemEntity> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItemEntity> lineItems)
    {
        this.lineItems = lineItems;
    }

    public List<PaymentTransactionEntity> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(List<PaymentTransactionEntity> transactions)
    {
        this.transactions = transactions;
    }
}