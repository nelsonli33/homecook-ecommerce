package com.homecook.tappay.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecordFiltersCardholder
{
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

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

    @Override
    public String toString()
    {
        return "RecordFiltersCardholder{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public static final class Builder
    {
        private String phoneNumber;
        private String name;
        private String email;

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

        public RecordFiltersCardholder build()
        {
            RecordFiltersCardholder recordFiltersCardholder = new RecordFiltersCardholder();
            recordFiltersCardholder.setPhoneNumber(phoneNumber);
            recordFiltersCardholder.setName(name);
            recordFiltersCardholder.setEmail(email);
            return recordFiltersCardholder;
        }
    }
}
