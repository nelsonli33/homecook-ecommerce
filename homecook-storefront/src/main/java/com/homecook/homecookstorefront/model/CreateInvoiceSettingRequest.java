package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateInvoiceSettingRequest
{
    @JsonProperty("invoiceType")
    private String invoiceType;

    @JsonProperty("email")
    private String email;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("businessNumber")
    private String businessNumber;

    @JsonProperty("charityLovecode")
    private String charityLovecode;

    @JsonProperty("charityName")
    private String charityName;

    public String getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
}
