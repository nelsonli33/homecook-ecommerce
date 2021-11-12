package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.InvoiceSettingEntity;
import com.homecook.homecookentity.entity.ShippingModeEntity;
import com.homecook.homecookentity.type.ShippingModeType;
import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.facade.CustomerFacade;
import com.homecook.homecookstorefront.facade.mapper.CustomerMapper;
import com.homecook.homecookstorefront.service.CustomerAccountService;
import com.homecook.homecookstorefront.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "customerFacade")
public class DefaultCustomerFacade implements CustomerFacade
{
    private CustomerAccountService customerAccountService;
    private CustomerService customerService;
    private CustomerMapper customerMapper;

    @Autowired
    public DefaultCustomerFacade(
            CustomerAccountService customerAccountService,
            CustomerService customerService,
            CustomerMapper customerMapper
    )
    {
        this.customerAccountService = customerAccountService;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO register(RegisterDTO registerDTO)
    {
        validateParameterNotNullStandardMessage("registerDTO", registerDTO);
        Assert.hasText(registerDTO.getAccount(), "The field [Account] cannot be empty");

        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setAccount(registerDTO.getAccount());
        newCustomer.setEmail(registerDTO.getEmail());

        final CustomerEntity registeredCustomer = getCustomerAccountService().register(newCustomer, registerDTO.getPassword());
        return getCustomerMapper().convertToCustomerDTO(registeredCustomer);
    }

    @Override
    public CustomerDTO getCurrentCustomer()
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        return getCustomerMapper().convertToCustomerDTO(currentCustomer);
    }

    @Override
    public CustomerDTO updateProfile(CustomerDTO customerDTO)
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        final CustomerEntity updatedCustomer
                = getCustomerAccountService().updateProfile(currentCustomer, customerDTO.getName(), customerDTO.getGender(), customerDTO.getBirthday());

        return getCustomerMapper().convertToCustomerDTO(updatedCustomer);
    }

    @Override
    public List<AddressDTO> getAddresses()
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        final List<AddressEntity> addressEntities = getCustomerAccountService().getAddresses(currentCustomer);
        return getCustomerMapper().convertToAddressDTOs(addressEntities);
    }

    @Override
    public AddressDTO getAddress(Long addressId)
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        final AddressEntity addressEntity = getCustomerAccountService().getAddressForId(currentCustomer, addressId);
        return getCustomerMapper().convertToAddressDTO(addressEntity);
    }


    @Override
    public AddressDTO addAddress(AddressDTO addressDTO)
    {

        validateParameterNotNullStandardMessage("addressDTO", addressDTO);

        final AddressEntity newAddress = getCustomerMapper().convertTAddressEntity(addressDTO);

        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();
        // store the address against the customer
        getCustomerAccountService().saveAddress(currentCustomer, newAddress);

        final boolean isMakeThisDefaultAddress = isHomeShippingAddress(newAddress) &&
                (addressDTO.isDefaultAddress() || currentCustomer.getDefaultAddress() == null);

        if (isMakeThisDefaultAddress)
        {
            getCustomerAccountService().setDefaultAddress(currentCustomer, newAddress);
        }

        return getCustomerMapper().convertToAddressDTO(newAddress);
    }

    @Override
    public AddressDTO editAddress(AddressDTO addressDTO)
    {
        validateParameterNotNullStandardMessage("addressDTO", addressDTO);

        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        final AddressEntity addressEntity = getCustomerAccountService().getAddressForId(currentCustomer, addressDTO.getId());
        getCustomerMapper().updateAddressEntity(addressDTO, addressEntity);
        getCustomerAccountService().saveAddress(currentCustomer, addressEntity);

        if (isHomeShippingAddress(addressEntity) && addressDTO.isDefaultAddress())
        {
            if (addressDTO.isDefaultAddress())
            {
                getCustomerAccountService().setDefaultAddress(currentCustomer, addressEntity);
            }
            else if (addressEntity.equals(currentCustomer.getDefaultAddress()))
            {
                getCustomerAccountService().clearDefaultAddress(currentCustomer);
            }
        }

        return getCustomerMapper().convertToAddressDTO(addressEntity);
    }

    @Override
    public void deleteAddress(Long addressId)
    {
        validateParameterNotNullStandardMessage("addressId", addressId);

        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        for (final AddressEntity address : getCustomerAccountService().getAddresses(currentCustomer))
        {
            if (address.getId().equals(addressId))
            {
                getCustomerAccountService().deleteAddress(currentCustomer, address);
                break;
            }
        }
    }


    protected boolean isHomeShippingAddress(AddressEntity addressEntity)
    {
        final ShippingModeEntity shippingModeEntity = addressEntity.getShippingMode();
        if (shippingModeEntity == null)
        {
            return false;
        }
        return ShippingModeType.HOME.equals(shippingModeEntity.getShippingModeType());
    }

    @Override
    public InvoiceSettingDTO getCustomerInvoiceSetting()
    {

        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        InvoiceSettingDTO dto = new InvoiceSettingDTO();
        dto.setPersonal(getCustomerMapper().convertToPersonalInvoiceDTO(currentCustomer));
        dto.setCompany(getCustomerMapper().convertToCompanyInvoiceDTO(currentCustomer.getCompanyInvoiceSetting()));
        dto.setDonation(getCustomerMapper().convertToDonationInvoiceDTO(currentCustomer.getDonationInvoiceSetting()));
        return dto;
    }

    @Override
    public CompanyInvoiceDTO setCompanyInvoiceSetting(CompanyInvoiceDTO companyInvoiceDTO)
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        InvoiceSettingEntity companyInvoiceSetting = getCustomerAccountService().getCompanyInvoiceSetting(currentCustomer);

        if (companyInvoiceSetting == null)
        {
            companyInvoiceSetting = getCustomerMapper().convertToInvoiceSettingEntity(companyInvoiceDTO);
        }
        else
        {
            getCustomerMapper().updateInvoiceSettingEntity(companyInvoiceDTO, companyInvoiceSetting);
        }

        getCustomerAccountService().saveCompanyInvoiceSetting(currentCustomer, companyInvoiceSetting);

        return getCustomerMapper().convertToCompanyInvoiceDTO(companyInvoiceSetting);
    }

    @Override
    public DonationInvoiceDTO setDonationInvoiceSetting(DonationInvoiceDTO donationInvoiceDTO)
    {
        final CustomerEntity currentCustomer = getCustomerService().getCurrentCustomer();

        InvoiceSettingEntity donationInvoiceSetting = getCustomerAccountService().getDonationInvoiceSetting(currentCustomer);

        if (donationInvoiceSetting == null)
        {
            donationInvoiceSetting = getCustomerMapper().convertToInvoiceSettingEntity(donationInvoiceDTO);
        }
        else
        {
            getCustomerMapper().updateInvoiceSettingEntity(donationInvoiceDTO, donationInvoiceSetting);
        }
        getCustomerAccountService().saveDonationInvoiceSetting(currentCustomer, donationInvoiceSetting);

        return getCustomerMapper().convertToDonationInvoiceDTO(donationInvoiceSetting);
    }


    public CustomerAccountService getCustomerAccountService()
    {
        return customerAccountService;
    }

    public void setCustomerAccountService(CustomerAccountService customerAccountService)
    {
        this.customerAccountService = customerAccountService;
    }

    public CustomerService getCustomerService()
    {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CustomerMapper getCustomerMapper()
    {
        return customerMapper;
    }

    public void setCustomerMapper(CustomerMapper customerMapper)
    {
        this.customerMapper = customerMapper;
    }
}
