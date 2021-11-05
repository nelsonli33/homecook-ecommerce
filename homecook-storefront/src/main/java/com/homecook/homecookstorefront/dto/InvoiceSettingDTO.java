package com.homecook.homecookstorefront.dto;

public class InvoiceSettingDTO
{
    private PersonalInvoiceDTO personal;
    private CompanyInvoiceDTO company;
    private DonationInvoiceDTO donation;

    public PersonalInvoiceDTO getPersonal()
    {
        return personal;
    }

    public void setPersonal(PersonalInvoiceDTO personal)
    {
        this.personal = personal;
    }

    public CompanyInvoiceDTO getCompany()
    {
        return company;
    }

    public void setCompany(CompanyInvoiceDTO company)
    {
        this.company = company;
    }

    public DonationInvoiceDTO getDonation()
    {
        return donation;
    }

    public void setDonation(DonationInvoiceDTO donation)
    {
        this.donation = donation;
    }
}
