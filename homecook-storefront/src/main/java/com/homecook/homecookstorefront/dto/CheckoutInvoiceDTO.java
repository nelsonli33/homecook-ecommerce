package com.homecook.homecookstorefront.dto;

import com.homecook.homecookentity.type.InvoiceType;

public class CheckoutInvoiceDTO
{
    private InvoiceType invoiceType;
    private String contactEmail;
    private String companyName;
    private String businessNumber;
    private String charityLovecode;
    private String charityName;
    private Boolean saveInvoice;

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

    public Boolean getSaveInvoice()
    {
        return saveInvoice;
    }

    public void setSaveInvoice(Boolean saveInvoice)
    {
        this.saveInvoice = saveInvoice;
    }
}
