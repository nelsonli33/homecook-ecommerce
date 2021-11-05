package com.homecook.homecookstorefront.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAddressRequest
{
    @JsonProperty("code")
    private String code;

    @JsonProperty("city")
    private String city;

    @JsonProperty("district")
    private String district;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("storeId")
    private String storeId;

    @JsonProperty("storeName")
    private String storeName;

    @JsonProperty("isDefaultAddress")
    private Boolean isDefaultAddress;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getDistrict()
    {
        return district;
    }

    public void setDistrict(String district)
    {
        this.district = district;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getStoreId()
    {
        return storeId;
    }

    public void setStoreId(String storeId)
    {
        this.storeId = storeId;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public Boolean getDefaultAddress()
    {
        return isDefaultAddress;
    }

    public void setDefaultAddress(Boolean defaultAddress)
    {
        isDefaultAddress = defaultAddress;
    }
}
