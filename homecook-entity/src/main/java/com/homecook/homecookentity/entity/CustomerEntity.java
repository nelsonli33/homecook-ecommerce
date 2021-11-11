package com.homecook.homecookentity.entity;

import com.homecook.homecookentity.constant.EntityConstant;
import com.homecook.homecookentity.type.InvoiceType;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Table(name = EntityConstant.Table.Customer)
@Entity
public class CustomerEntity extends AbstractBaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "account", nullable = false, unique = true)
    private String account;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AddressEntity> addresses = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_address_id")
    private AddressEntity defaultAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "last_checkout_shipment_address")
    private AddressEntity lastCheckoutShipmentAddress;

    @Column(name = "default_invoice_type")
    private InvoiceType defaultInvoiceType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_invoice_setting_id")
    private InvoiceSettingEntity companyInvoiceSetting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_invoice_setting_id")
    private InvoiceSettingEntity donationInvoiceSetting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_carrier_id")
    private InvoiceCarrierEntity invoiceCarrier;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_payment_mode_id")
    private PaymentModeEntity defaultPaymentMode;

    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> order;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VoucherReceiveEntity> voucherReceives;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public List<AddressEntity> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses)
    {
        this.addresses = addresses;
    }

    public AddressEntity getDefaultAddress()
    {
        return defaultAddress;
    }

    public void setDefaultAddress(AddressEntity defaultAddress)
    {
        this.defaultAddress = defaultAddress;
    }

    public AddressEntity getLastCheckoutShipmentAddress()
    {
        return lastCheckoutShipmentAddress;
    }

    public void setLastCheckoutShipmentAddress(AddressEntity lastCheckoutShipmentAddress)
    {
        this.lastCheckoutShipmentAddress = lastCheckoutShipmentAddress;
    }

    public InvoiceSettingEntity getCompanyInvoiceSetting()
    {
        return companyInvoiceSetting;
    }

    public void setCompanyInvoiceSetting(InvoiceSettingEntity companyInvoiceSetting)
    {
        this.companyInvoiceSetting = companyInvoiceSetting;
    }

    public InvoiceSettingEntity getDonationInvoiceSetting()
    {
        return donationInvoiceSetting;
    }

    public void setDonationInvoiceSetting(InvoiceSettingEntity donationInvoiceSetting)
    {
        this.donationInvoiceSetting = donationInvoiceSetting;
    }

    public InvoiceType getDefaultInvoiceType()
    {
        return defaultInvoiceType;
    }

    public void setDefaultInvoiceType(InvoiceType defaultInvoiceType)
    {
        this.defaultInvoiceType = defaultInvoiceType;
    }

    public InvoiceCarrierEntity getInvoiceCarrier()
    {
        return invoiceCarrier;
    }

    public void setInvoiceCarrier(InvoiceCarrierEntity invoiceCarrier)
    {
        this.invoiceCarrier = invoiceCarrier;
    }


    public PaymentModeEntity getDefaultPaymentMode()
    {
        return defaultPaymentMode;
    }

    public void setDefaultPaymentMode(PaymentModeEntity defaultPaymentMode)
    {
        this.defaultPaymentMode = defaultPaymentMode;
    }

    public List<OrderEntity> getOrder()
    {
        return order;
    }

    public void setOrder(List<OrderEntity> order)
    {
        this.order = order;
    }

    public List<VoucherReceiveEntity> getVoucherReceives()
    {
        return voucherReceives;
    }

    public void setVoucherReceives(List<VoucherReceiveEntity> voucherReceives)
    {
        this.voucherReceives = voucherReceives;
    }
}