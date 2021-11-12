package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.*;
import org.mapstruct.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface CustomerRestMapper
{

    @Mappings({
            @Mapping(target = "email", expression = "java(com.homecook.homecookstorefront.util.MaskUtil.maskEmailAddress(customerDTO.getEmail()))"),
            @Mapping(target = "phone", expression = "java(com.homecook.homecookstorefront.util.MaskUtil.maskPhoneNumber(customerDTO.getPhone()))"),
            @Mapping(target = "birthday", dateFormat = "yyyy-MM-dd")
    })
    CustomerProfile toCustomerProfile(CustomerDTO customerDTO);


    @Mapping(target = "birthday", qualifiedByName = "birthdayConverter")
    void updateCustomerProfile(UpdateCustomerProfileRequest request, @MappingTarget CustomerDTO customerDTO);

    @Named(value = "birthdayConverter")
    default Date birthdayConverter(String birthday)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return dateFormat.parse(birthday);
        }
        catch (ParseException e)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CUSTOMER_BIRTHDAY_FORMAT_INVALID, "birthday supported format: yyyy-MM-dd");
        }
    }

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
