package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "personal",
        "company",
        "donation"
})
public class CustomerInvoiceSetting
{
    @JsonProperty("personal")
    private PersonalInvoiceSetting personal;

    @JsonProperty("company")
    private CompanyInvoiceSetting company;

    @JsonProperty("donation")
    private DonationInvoiceSetting donation;

    public PersonalInvoiceSetting getPersonal()
    {
        return personal;
    }

    public void setPersonal(PersonalInvoiceSetting personal)
    {
        this.personal = personal;
    }

    public CompanyInvoiceSetting getCompany()
    {
        return company;
    }

    public void setCompany(CompanyInvoiceSetting company)
    {
        this.company = company;
    }

    public DonationInvoiceSetting getDonation()
    {
        return donation;
    }

    public void setDonation(DonationInvoiceSetting donation)
    {
        this.donation = donation;
    }
}
