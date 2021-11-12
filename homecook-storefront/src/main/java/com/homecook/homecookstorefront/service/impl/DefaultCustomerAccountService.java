package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.InvoiceSettingEntity;
import com.homecook.homecookentity.entity.ShippingModeEntity;
import com.homecook.homecookentity.repository.CustomerRepository;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookentity.type.GenderType;
import com.homecook.homecookentity.type.ShippingModeType;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CustomerAccountService;
import com.homecook.homecookstorefront.service.InvoiceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "customerAccountService")
public class DefaultCustomerAccountService implements CustomerAccountService
{
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    private ModelService modelService;
    private InvoiceService invoiceService;

    @Autowired
    public DefaultCustomerAccountService(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            ModelService modelService,
            @Qualifier(value = "ecpayInvoiceService") InvoiceService invoiceService)
    {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelService = modelService;
        this.invoiceService = invoiceService;
    }


    @Override
    public CustomerEntity register(CustomerEntity customerEntity, String password)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        checkAccountIsExists(customerEntity);

        if (StringUtils.isNotEmpty(password))
        {
            String encodedPassword = getPasswordEncoder().encode(password);
            customerEntity.setPassword(encodedPassword);
        }

        return getCustomerRepository().save(customerEntity);
    }

    @Override
    public void changePassword(CustomerEntity customerEntity, String oldPassword, String newPassword)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        if (getPasswordEncoder().matches(oldPassword, customerEntity.getPassword()))
        {
            String encodedPassword = getPasswordEncoder().encode(newPassword);
            customerEntity.setPassword(encodedPassword);
            getModelService().save(customerEntity);
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CUSTOMER_PASSWORD_MISMATCH, "Password mismatch");
        }
    }

    @Override
    public CustomerEntity updateProfile(CustomerEntity customerEntity, String name, Integer gender, Date birthday)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        customerEntity.setName(name);
        customerEntity.setGender(GenderType.valueOf(gender));
        customerEntity.setBirthday(birthday);

        return getCustomerRepository().save(customerEntity);
    }


    @Override
    public List<AddressEntity> getAddresses(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        return customerEntity.getAddresses();
    }

    @Override
    public AddressEntity getAddressForId(CustomerEntity customerEntity, Long addressId)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        for (final AddressEntity address : customerEntity.getAddresses())
        {
            if (address.getId().equals(addressId))
            {
                return address;
            }
        }

        throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Address with id " + addressId + " not found.");
    }

    @Override
    public void saveAddress(CustomerEntity customerEntity, AddressEntity addressEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("addressEntity", addressEntity);

        final List<AddressEntity> addresses = new ArrayList<>();
        addresses.addAll(customerEntity.getAddresses());

        if (customerEntity.getAddresses().contains(addressEntity))
        {
            getModelService().save(addressEntity);
        }
        else
        {
            addressEntity.setCustomer(customerEntity);
            getModelService().save(addressEntity);

            addresses.add(addressEntity);
        }
        customerEntity.getAddresses().clear();
        customerEntity.getAddresses().addAll(addresses);
        getModelService().save(customerEntity);
    }

    @Override
    public void deleteAddress(CustomerEntity customerEntity, AddressEntity addressEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("addressEntity", addressEntity);

        if (customerEntity.getAddresses().contains(addressEntity))
        {

            customerEntity.getAddresses().remove(addressEntity);
            getModelService().save(customerEntity);

            final boolean changeDefaultAddress = addressEntity.equals(getDefaultAddress(customerEntity));
            final Iterator<AddressEntity> addressModelIterator = customerEntity.getAddresses().iterator();


            while (changeDefaultAddress && addressModelIterator.hasNext())
            {
                final AddressEntity next = addressModelIterator.next();
                if (next.getShippingMode() != null && ShippingModeType.HOME.equals(next.getShippingMode().getShippingModeType()))
                {
                    setDefaultAddress(customerEntity, next);
                    break;
                }
            }
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Address with id " + addressEntity.getId() + " not found. It cann not be removed.");
        }
    }

    public void setDefaultAddress(CustomerEntity customerEntity, AddressEntity addressEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("addressEntity", addressEntity);

        if (customerEntity.getAddresses().contains(addressEntity))
        {
            customerEntity.setDefaultAddress(addressEntity);
        }
        getModelService().save(customerEntity);
    }

    @Override
    public AddressEntity getDefaultAddress(CustomerEntity customerEntity)
    {
        return customerEntity.getDefaultAddress();
    }

    @Override
    public void clearDefaultAddress(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        customerEntity.setDefaultAddress(null);
        getModelService().save(customerEntity);
    }

    @Override
    public List<AddressEntity> getAddressesForShippingMode(CustomerEntity customerEntity, ShippingModeEntity shippingModeEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("shippingModeEntity", customerEntity);

        List<AddressEntity> addresses = new ArrayList<>();

        for (AddressEntity address : customerEntity.getAddresses())
        {
            if (address.getShippingMode().equals(shippingModeEntity))
            {
                addresses.add(address);
            }
        }

        return addresses;
    }

    @Override
    public InvoiceSettingEntity getCompanyInvoiceSetting(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        return customerEntity.getCompanyInvoiceSetting();
    }

    @Override
    public InvoiceSettingEntity getDonationInvoiceSetting(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        return customerEntity.getDonationInvoiceSetting();
    }

    @Override
    public void saveCompanyInvoiceSetting(CustomerEntity customerEntity, InvoiceSettingEntity invoiceSettingEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("invoiceSettingEntity", invoiceSettingEntity);

        if (customerEntity.getCompanyInvoiceSetting() != null && customerEntity.getCompanyInvoiceSetting().equals(invoiceSettingEntity))
        {
            getModelService().save(invoiceSettingEntity);
        }
        else
        {
            customerEntity.setCompanyInvoiceSetting(invoiceSettingEntity);
            getModelService().save(invoiceSettingEntity);
            getModelService().save(customerEntity);
        }
    }

    @Override
    public void saveDonationInvoiceSetting(CustomerEntity customerEntity, InvoiceSettingEntity invoiceSettingEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);
        validateParameterNotNullStandardMessage("invoiceSettingEntity", invoiceSettingEntity);

        boolean isValidLoveCode = getInvoiceService().checkLoveCode(invoiceSettingEntity.getCharityLovecode());

        if (!isValidLoveCode)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.INVOICE_LOVE_CODE_NOT_EXIST, "Love code not exist.");
        }

        if (customerEntity.getDonationInvoiceSetting() != null && customerEntity.getDonationInvoiceSetting().equals(invoiceSettingEntity))
        {
            getModelService().save(invoiceSettingEntity);
        }
        else
        {
            customerEntity.setDonationInvoiceSetting(invoiceSettingEntity);
            getModelService().save(invoiceSettingEntity);
            getModelService().save(customerEntity);
        }
    }

    private void checkAccountIsExists(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        boolean exists = false;

        if (StringUtils.isNotEmpty(customerEntity.getAccount()))
        {
            exists = getCustomerRepository().existsByAccountIgnoreCase(customerEntity.getAccount());
        }

        if (StringUtils.isNotEmpty(customerEntity.getEmail()))
        {
            exists = getCustomerRepository().existsByEmailIgnoreCase(customerEntity.getEmail());
        }

        if (exists)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CUSTOMER_ACCOUNT_EXISTS, "the customer already exist.");
        }
    }


    public CustomerRepository getCustomerRepository()
    {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public InvoiceService getInvoiceService()
    {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService)
    {
        this.invoiceService = invoiceService;
    }
}

