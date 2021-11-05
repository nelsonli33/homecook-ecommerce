package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.InvoiceStatusType;
import com.homecook.homecookentity.type.InvoiceType;

import javax.persistence.*;
import java.util.Date;

@Table(name = EntityConstant.Table.OrderInvoice)
@Entity
public class OrderInvoiceEntity extends AbstractBaseEntity
{
    @Column(name = "invoice_type")
    private InvoiceType invoiceType;

    @Column(name = "invoice_title")
    private String invoiceTitle;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "love_code")
    private String loveCode;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "invoice_date")
    private Date invoiceDate; // 開票日期

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "gateway_message")
    private String gatewayMessage;

    @Column(name = "invoice_status")
    private InvoiceStatusType invoiceStatus;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "invoice")
    private OrderEntity orderEntity;

    public InvoiceType getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle()
    {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle)
    {
        this.invoiceTitle = invoiceTitle;
    }

    public String getBusinessNumber()
    {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber)
    {
        this.businessNumber = businessNumber;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getLoveCode()
    {
        return loveCode;
    }

    public void setLoveCode(String loveCode)
    {
        this.loveCode = loveCode;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getInvoiceDate()
    {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getGatewayMessage()
    {
        return gatewayMessage;
    }

    public void setGatewayMessage(String gatewayMessage)
    {
        this.gatewayMessage = gatewayMessage;
    }

    public InvoiceStatusType getInvoiceStatus()
    {
        return invoiceStatus;
    }

    public void setInvoiceStatus(InvoiceStatusType invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    public OrderEntity getOrderEntity()
    {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity)
    {
        this.orderEntity = orderEntity;
    }
}
