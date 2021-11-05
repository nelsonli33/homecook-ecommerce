package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderInvoice
{
    @JsonProperty("invoiceType")
    private String invoiceType;

    @JsonProperty("invoiceTitle")
    private String invoiceTitle;

    @JsonProperty("businessNumber")
    private String businessNumber;

    @JsonProperty("contactEmail")
    private String contactEmail;

    @JsonProperty("loveCode")
    private String loveCode;

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;

    @JsonProperty("invoiceDate")
    private Date invoiceDate;

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
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
}
