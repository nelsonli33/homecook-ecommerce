package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cardholder
{
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("zip_code")
    private String zipCode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("national_id")
    private String nationalId;

    @JsonProperty("member_id")
    private String memberId;

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getNationalId()
    {
        return nationalId;
    }

    public void setNationalId(String nationalId)
    {
        this.nationalId = nationalId;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }


    public static final class Builder
    {
        private String phoneNumber;
        private String name;
        private String email;
        private String zipCode;
        private String address;
        private String nationalId;
        private String memberId;


        public Builder phoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder email(String email)
        {
            this.email = email;
            return this;
        }

        public Builder zipCode(String zipCode)
        {
            this.zipCode = zipCode;
            return this;
        }

        public Builder address(String address)
        {
            this.address = address;
            return this;
        }

        public Builder nationalId(String nationalId)
        {
            this.nationalId = nationalId;
            return this;
        }

        public Builder memberId(String memberId)
        {
            this.memberId = memberId;
            return this;
        }

        public Cardholder build()
        {
            Cardholder cardholder = new Cardholder();
            cardholder.setPhoneNumber(phoneNumber);
            cardholder.setName(name);
            cardholder.setEmail(email);
            cardholder.setZipCode(zipCode);
            cardholder.setAddress(address);
            cardholder.setNationalId(nationalId);
            cardholder.setMemberId(memberId);
            return cardholder;
        }
    }
}
