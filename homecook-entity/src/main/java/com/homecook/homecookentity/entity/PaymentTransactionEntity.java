package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.PaymentTransactionStatus;
import com.homecook.homecookentity.type.PaymentTransactionType;

import javax.persistence.*;
import java.util.Date;

@Table(name = EntityConstant.Table.PaymentTransaction)
@Entity
public class PaymentTransactionEntity extends AbstractBaseEntity
{
    @Column(name = "amount")
    private Integer amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "gateway_transaction_id")
    private String gatewayTransactionId;

    @Column(name = "bank_transaction_id")
    private String bankTransactionId;

    @Column(name = "transaction_type")
    private PaymentTransactionType transactionType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "gateway_message")
    private String gatewayMessage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "transaction")
    private CreditCardPaymentInfoEntity creditCardPaymentInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PaymentTransactionEntity parent;

    @Column(name = "transaction_status")
    private PaymentTransactionStatus transactionStatus;

    public Integer getAmount()
    {
        return amount;
    }

    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(String currency)
    {
        this.currency = currency;
    }

    public String getGatewayTransactionId()
    {
        return gatewayTransactionId;
    }

    public void setGatewayTransactionId(String gatewayTransactionId)
    {
        this.gatewayTransactionId = gatewayTransactionId;
    }

    public String getBankTransactionId()
    {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId)
    {
        this.bankTransactionId = bankTransactionId;
    }

    public PaymentTransactionType getTransactionType()
    {
        return transactionType;
    }

    public void setTransactionType(PaymentTransactionType transactionType)
    {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate()
    {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public String getGatewayMessage()
    {
        return gatewayMessage;
    }

    public void setGatewayMessage(String gatewayMessage)
    {
        this.gatewayMessage = gatewayMessage;
    }

    public OrderEntity getOrder()
    {
        return order;
    }

    public void setOrder(OrderEntity order)
    {
        this.order = order;
    }

    public CreditCardPaymentInfoEntity getCreditCardPaymentInfo()
    {
        return creditCardPaymentInfo;
    }

    public void setCreditCardPaymentInfo(CreditCardPaymentInfoEntity creditCardPaymentInfo)
    {
        this.creditCardPaymentInfo = creditCardPaymentInfo;
    }

    public PaymentTransactionEntity getParent()
    {
        return parent;
    }

    public void setParent(PaymentTransactionEntity parent)
    {
        this.parent = parent;
    }

    public PaymentTransactionStatus getTransactionStatus()
    {
        return transactionStatus;
    }

    public void setTransactionStatus(PaymentTransactionStatus transactionStatus)
    {
        this.transactionStatus = transactionStatus;
    }
}
