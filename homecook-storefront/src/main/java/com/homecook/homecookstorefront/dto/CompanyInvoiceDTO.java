package com.homecook.homecookstorefront.dto;

public class CompanyInvoiceDTO
{
    private String email;
    private String companyName;
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
