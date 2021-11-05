package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.InvoiceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = EntityConstant.Table.InvoiceSetting)
@Entity
public class InvoiceSettingEntity extends AbstractBaseEntity
{
    @Column(name = "invoice_type")
    private InvoiceType invoiceType;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "invoice_title")
    private String invoiceTitle;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "charity_lovecode")
    private String charityLovecode;

    @Column(name = "charity_name")
    private String charityName;


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

    public String getCharityLovecode()
    {
        return charityLovecode;
    }

    public void setCharityLovecode(String charityLovecode)
    {
        this.charityLovecode = charityLovecode;
    }

    public String getCharityName()
    {
        return charityName;
    }

    public void setCharityName(String charityName)
    {
        this.charityName = charityName;
    }

}
