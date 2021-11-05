package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.InvoiceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = EntityConstant.Table.CheckoutInvoice)
@Entity
public class CheckoutInvoiceEntity extends AbstractBaseEntity
{
    @Column(name = "invoice_type")
    private InvoiceType invoiceType;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "charity_lovecode")
    private String charityLovecode;

    @Column(name = "charity_name")
    private String charityName;

    @Column(name = "checkout_code")
    private String checkoutCode;

    public InvoiceType getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getContactEmail()
    {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail)
    {
        this.contactEmail = contactEmail;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public String getBusinessNumber()
    {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber)
    {
        this.businessNumber = businessNumber;
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

    public String getCheckoutCode()
    {
        return checkoutCode;
    }

    public void setCheckoutCode(String checkoutCode)
    {
        this.checkoutCode = checkoutCode;
    }
}
