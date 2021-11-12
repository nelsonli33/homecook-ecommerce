package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CustomerRestMapper
{

    @Mappings({
            @Mapping(target = "email", expression = "java(com.homecook.homecookstorefront.util.MaskUtil.maskEmailAddress(customerDTO.getEmail()))"),
            @Mapping(target = "phone", expression = "java(com.homecook.homecookstorefront.util.MaskUtil.maskPhoneNumber(customerDTO.getPhone()))")
    })
    CustomerProfile toCustomerProfile(CustomerDTO customerDTO);


    @Mapping(source = "code", target = "shippingModeCode")
    AddressDTO toCustomerAddressDTO(CreateAddressRequest createAddressRequest);

    @Mapping(source = "code", target = "shippingModeCode")
    AddressDTO toCustomerAddressDTO(UpdateAddressRequest updateAddressRequest);

    @Mappings({
            @Mapping(source = "shippingMode.code", target = "code"),
            @Mapping(source = "shippingMode.name", target = "type"),
    })
    Address toCustomerAddress(AddressDTO addressDTO);

    List<Address> toCustomerAddresses(List<AddressDTO> addressDTOs);

    CompanyInvoiceDTO toCompanyInvoiceDTO(CreateCompanyInvoiceSettingRequest createCompanyInvoiceSettingRequest);

    DonationInvoiceDTO toDonationInvoiceDTO(CreateDonationInvoiceSettingRequest createDonationInvoiceSettingRequest);

    CompanyInvoiceDTO toCompanyInvoiceDTO(UpdateCompanyInvoiceSettingRequest updateCompanyInvoiceSettingRequest);

    DonationInvoiceDTO toDonationInvoiceDTO(UpdateDonationInvoiceSettingRequest updateDonationInvoiceSettingRequest);

    CustomerInvoiceSetting toCustomerInvoiceSetting(InvoiceSettingDTO invoiceSettingDTO);

    PersonalInvoiceSetting toPersonalInvoiceSetting(PersonalInvoiceDTO personalInvoiceDTO);

    CompanyInvoiceSetting toCompanyInvoiceSetting(CompanyInvoiceDTO companyInvoiceDTO);

    DonationInvoiceSetting toDonationInvoiceSetting(DonationInvoiceDTO donationInvoice);
}
