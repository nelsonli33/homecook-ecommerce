package com.homecook.homecookstorefront.facade;

import com.homecook.homecookstorefront.dto.*;

import java.util.List;

public interface CustomerFacade
{
    CustomerDTO register(RegisterDTO registerDTO);

    CustomerDTO getCustomerProfile();

    List<AddressDTO> getAddresses();

    AddressDTO getAddress(Long addressId);

    AddressDTO editAddress(AddressDTO addressDTO);

    AddressDTO addAddress(AddressDTO addressDTO);

    void deleteAddress(Long addressId);

    InvoiceSettingDTO getCustomerInvoiceSetting();

    CompanyInvoiceDTO setCompanyInvoiceSetting(CompanyInvoiceDTO companyInvoiceDTO);

    DonationInvoiceDTO setDonationInvoiceSetting(DonationInvoiceDTO donationInvoiceDTO);
}
