package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCompanyInvoiceSettingRequest
{
    @JsonProperty("email")
    private String email;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("businessNumber")
    private String businessNumber;

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
}
