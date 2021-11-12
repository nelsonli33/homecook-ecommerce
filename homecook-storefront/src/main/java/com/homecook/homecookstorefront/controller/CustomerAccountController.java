package com.homecook.homecookstorefront.controller;

import com.homecook.homecookstorefront.controller.mapper.CustomerRestMapper;
import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.facade.CustomerFacade;
import com.homecook.homecookstorefront.model.*;
import com.homecook.homecookstorefront.util.validator.AddressReqMsgValidator;
import com.homecook.homecookstorefront.util.validator.CustomerAccountReqMsgValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerAccountController
{
    private CustomerFacade customerFacade;
    private CustomerRestMapper customerRestMapper;
    private AddressReqMsgValidator addressReqMsgValidator;
    private CustomerAccountReqMsgValidator customerAccountReqMsgValidator;

    @Autowired
    public CustomerAccountController(
            CustomerFacade customerFacade,
            CustomerRestMapper customerRestMapper,
            AddressReqMsgValidator addressReqMsgValidator,
            CustomerAccountReqMsgValidator customerAccountReqMsgValidator
    )
    {
        this.customerFacade = customerFacade;
        this.customerRestMapper = customerRestMapper;
        this.addressReqMsgValidator = addressReqMsgValidator;
        this.customerAccountReqMsgValidator = customerAccountReqMsgValidator;
    }

    @GetMapping(value = "/api/v1/customer/account/profile")
    public ResponseEntity<CustomerProfile> getProfile()
    {
        final CustomerDTO customerDTO = customerFacade.getCurrentCustomer();
        final CustomerProfile customerProfile = customerRestMapper.toCustomerProfile(customerDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerProfile);
    }

    @PutMapping(value = "/api/v1/customer/account/profile")
    public ResponseEntity<CustomerProfile> updateProfile(@RequestBody UpdateCustomerProfileRequest request)
    {

        final CustomerDTO customerDTO = customerFacade.getCurrentCustomer();
        customerRestMapper.updateCustomerProfile(request, customerDTO);

        final CustomerDTO updatedCustomerDTO = customerFacade.updateProfile(customerDTO);
        final CustomerProfile updatedCustomerProfile = customerRestMapper.toCustomerProfile(updatedCustomerDTO);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCustomerProfile);
    }

    @PostMapping(value = "/api/v1/customer/account/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequest request, BindingResult errors)
    {
        customerAccountReqMsgValidator.validateUpdatePasswordRequest(request, errors);

        customerFacade.changePassword(request.getCurrentPassword(), request.getNewPassword());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ServerResponse.success());
    }

    @GetMapping(value = "/api/v1/customer/account/addresses")
    public ResponseEntity<List<Address>> listAddresses()
    {
        final List<AddressDTO> addresses = customerFacade.getAddresses();
        final List<Address> customerAddresses = customerRestMapper.toCustomerAddresses(addresses);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAddresses);
    }

    @GetMapping(value = "/api/v1/customer/account/addresses/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable("addressId") Long addressId)
    {
        final AddressDTO address = customerFacade.getAddress(addressId);
        final Address customerAddress = customerRestMapper.toCustomerAddress(address);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAddress);
    }

    @PostMapping(value = "/api/v1/customer/account/addresses")
    public ResponseEntity<Address> addAddress(@RequestBody final CreateAddressRequest createAddressRequest, BindingResult bindingResult)
    {
        addressReqMsgValidator.validateCreateAddressRequest(createAddressRequest, bindingResult);
        final AddressDTO addressDTO = customerRestMapper.toCustomerAddressDTO(createAddressRequest);
        final AddressDTO customerAddressData = customerFacade.addAddress(addressDTO);
        final Address address = customerRestMapper.toCustomerAddress(customerAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(address);
    }

    @PutMapping(value = "/api/v1/customer/account/addresses/{addressId}")
    public ResponseEntity<Address> editAddress(@PathVariable("addressId") Long addressId,
                                               @RequestBody final UpdateAddressRequest updateAddressRequest)
    {
        final AddressDTO addressDTO = customerRestMapper.toCustomerAddressDTO(updateAddressRequest);
        addressDTO.setId(addressId);

        if (Boolean.TRUE.equals(updateAddressRequest.getDefaultAddress()) || customerFacade.getAddresses().size() <= 1)
        {
            addressDTO.setDefaultAddress(true);
        }

        final AddressDTO updatedAddressData = customerFacade.editAddress(addressDTO);
        final Address address = customerRestMapper.toCustomerAddress(updatedAddressData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(address);
    }

    @DeleteMapping(value = "/api/v1/customer/account/addresses/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") Long addressId)
    {
        customerFacade.deleteAddress(addressId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


    @GetMapping(value = "/api/v1/customer/account/invoice-settings")
    public ResponseEntity<CustomerInvoiceSetting> getCustomerInvoiceSetting()
    {
        final InvoiceSettingDTO invoiceSettingDTO = customerFacade.getCustomerInvoiceSetting();
        final CustomerInvoiceSetting customerInvoiceSetting
                = customerRestMapper.toCustomerInvoiceSetting(invoiceSettingDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerInvoiceSetting);
    }

    @PostMapping(value = "/api/v1/customer/account/invoice-settings/company")
    public ResponseEntity<CompanyInvoiceSetting> createCompanyInvoiceSettings(@RequestBody final CreateCompanyInvoiceSettingRequest createCompanyInvoiceSettingRequest)
    {

        final CompanyInvoiceDTO companyInvoiceDTO
                = customerRestMapper.toCompanyInvoiceDTO(createCompanyInvoiceSettingRequest);
        final CompanyInvoiceDTO companyInvoiceData = customerFacade.setCompanyInvoiceSetting(companyInvoiceDTO);
        final CompanyInvoiceSetting companyInvoiceSetting = customerRestMapper.toCompanyInvoiceSetting(companyInvoiceData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyInvoiceSetting);
    }

    @PutMapping(value = "/api/v1/customer/account/invoice-settings/company")
    public ResponseEntity<CompanyInvoiceSetting> editCompanyInvoiceSettings(@RequestBody final UpdateCompanyInvoiceSettingRequest updateCompanyInvoiceSettingRequest)
    {

        final CompanyInvoiceDTO companyInvoiceDTO
                = customerRestMapper.toCompanyInvoiceDTO(updateCompanyInvoiceSettingRequest);
        final CompanyInvoiceDTO companyInvoiceData = customerFacade.setCompanyInvoiceSetting(companyInvoiceDTO);
        final CompanyInvoiceSetting companyInvoiceSetting = customerRestMapper.toCompanyInvoiceSetting(companyInvoiceData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyInvoiceSetting);
    }

    @PostMapping(value = "/api/v1/customer/account/invoice-settings/donation")
    public ResponseEntity<DonationInvoiceSetting> createDonationInvoiceSettings(@RequestBody final CreateDonationInvoiceSettingRequest createDonationInvoiceSettingRequest)
    {

        final DonationInvoiceDTO donationInvoiceDTO
                = customerRestMapper.toDonationInvoiceDTO(createDonationInvoiceSettingRequest);
        final DonationInvoiceDTO donationInvoiceData = customerFacade.setDonationInvoiceSetting(donationInvoiceDTO);
        final DonationInvoiceSetting donationInvoiceSetting = customerRestMapper.toDonationInvoiceSetting(donationInvoiceData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(donationInvoiceSetting);
    }

    @PutMapping(value = "/api/v1/customer/account/invoice-settings/donation")
    public ResponseEntity<DonationInvoiceSetting> editDonationInvoiceSettings(@RequestBody final UpdateDonationInvoiceSettingRequest updateDonationInvoiceSettingRequest)
    {

        final DonationInvoiceDTO donationInvoiceDTO
                = customerRestMapper.toDonationInvoiceDTO(updateDonationInvoiceSettingRequest);
        final DonationInvoiceDTO donationInvoiceData = customerFacade.setDonationInvoiceSetting(donationInvoiceDTO);
        final DonationInvoiceSetting donationInvoiceSetting = customerRestMapper.toDonationInvoiceSetting(donationInvoiceData);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(donationInvoiceSetting);
    }
}
