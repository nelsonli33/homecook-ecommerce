package com.homecook.homecookstorefront.dto;

public class AddressDTO
{
    private Long id;
    private String country;
    private String city;
    private String district;
    private String zipcode;
    private String address;
    private String name;
    private String phone;
    private String storeId;
    private String storeName;
    private String shippingModeCode;
    private ShippingModeDTO shippingMode;
    private boolean defaultAddress;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
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

    public String getShippingModeCode()
    {
        return shippingModeCode;
    }

    public void setShippingModeCode(String shippingModeCode)
    {
        this.shippingModeCode = shippingModeCode;
    }

    public ShippingModeDTO getShippingMode()
    {
        return shippingMode;
    }

    public void setShippingMode(ShippingModeDTO shippingMode)
    {
        this.shippingMode = shippingMode;
    }

    public boolean isDefaultAddress()
    {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress)
    {
        this.defaultAddress = defaultAddress;
    }
}
