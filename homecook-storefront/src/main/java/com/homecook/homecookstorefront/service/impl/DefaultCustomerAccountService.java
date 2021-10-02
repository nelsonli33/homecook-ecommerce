package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.repository.CustomerRepository;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CustomerAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Service(value = "customerAccountService")
public class DefaultCustomerAccountService implements CustomerAccountService
{
    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultCustomerAccountService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder)
    {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public CustomerEntity register(CustomerEntity customerEntity, String password)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        checkAccountIsExists(customerEntity);

        if (StringUtils.isNotEmpty(password))
        {
            String encodedPassword = passwordEncoder.encode(password);
            customerEntity.setPassword(encodedPassword);
        }

        return customerRepository.save(customerEntity);
    }


    private void checkAccountIsExists(CustomerEntity customerEntity)
    {
        validateParameterNotNullStandardMessage("customerEntity", customerEntity);

        boolean exists = false;

        if (StringUtils.isNotEmpty(customerEntity.getAccount()))
        {
            exists = customerRepository.existsByAccountIgnoreCase(customerEntity.getAccount());
        }

        if (StringUtils.isNotEmpty(customerEntity.getEmail()))
        {
            exists = customerRepository.existsByEmailIgnoreCase(customerEntity.getEmail());
        }

        if (exists)
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.CUSTOMER_ACCOUNT_EXISTS, "the customer already exist.");
        }
    }
}

