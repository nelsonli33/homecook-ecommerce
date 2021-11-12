package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.InvoiceSettingEntity;
import com.homecook.homecookentity.entity.ShippingModeEntity;

import java.util.Date;
import java.util.List;

public interface CustomerAccountService
{
    CustomerEntity register(CustomerEntity customerEntity, String password);

    CustomerEntity updateProfile(CustomerEntity customerEntity, String name, Integer gender, Date birthday);

    List<AddressEntity> getAddresses(CustomerEntity customerEntity);

    AddressEntity getAddressForId(CustomerEntity customerEntity, Long addressId);

    void saveAddress(CustomerEntity customerEntity, AddressEntity addressEntity);

    void deleteAddress(CustomerEntity customerEntity, AddressEntity addressEntity);

    void setDefaultAddress(CustomerEntity customerEntity, AddressEntity addressEntity);

    AddressEntity getDefaultAddress(CustomerEntity customerEntity);

    void clearDefaultAddress(CustomerEntity customerEntity);

    List<AddressEntity> getAddressesForShippingMode(CustomerEntity customerEntity, ShippingModeEntity shippingModeEntity);

    InvoiceSettingEntity getCompanyInvoiceSetting(CustomerEntity customerEntity);

    InvoiceSettingEntity getDonationInvoiceSetting(CustomerEntity customerEntity);

    void saveCompanyInvoiceSetting(CustomerEntity customerEntity, InvoiceSettingEntity invoiceSettingEntity);

    void saveDonationInvoiceSetting(CustomerEntity customerEntity, InvoiceSettingEntity invoiceSettingEntity);
}
