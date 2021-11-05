package com.homecook.homecookstorefront.dto;

public class PersonalInvoiceDTO
{
    private String email;
    private AddressDTO address;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public AddressDTO getAddress()
    {
        return address;
    }

    public void setAddress(AddressDTO address)
    {
        this.address = address;
    }
}
