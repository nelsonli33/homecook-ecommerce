package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;

import javax.persistence.*;

@Table(name = EntityConstant.Table.Address)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER)
public class AddressEntity extends AbstractBaseEntity
{
    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "storeId")
    private String storeId;

    @Column(name = "storeName")
    private String storeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_mode_id")
    private ShippingModeEntity shippingMode;

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

    public CustomerEntity getCustomer()
    {
        return customer;
    }

    public void setCustomer(CustomerEntity customer)
    {
        this.customer = customer;
    }

    public ShippingModeEntity getShippingMode()
    {
        return shippingMode;
    }

    public void setShippingMode(ShippingModeEntity shippingMode)
    {
        this.shippingMode = shippingMode;
    }
}