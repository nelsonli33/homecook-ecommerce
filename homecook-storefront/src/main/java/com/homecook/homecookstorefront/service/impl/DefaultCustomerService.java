package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.repository.CustomerRepository;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "customerService")
public class DefaultCustomerService implements CustomerService
{
    private CustomerRepository customerRepository;

    @Autowired
    public DefaultCustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerEntity getCurrentCustomer()
    {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || StringUtils.isEmpty(authentication.getName()))
        {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }

        final Optional<CustomerEntity> customer = getCustomerForUid(authentication.getName());
        if (!customer.isPresent())
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "The customer with uid: " + authentication.getName() + " not found.");
        }
        return customer.get();
    }

    @Override
    public Optional<CustomerEntity> getCustomerForUid(String uid)
    {
        CustomerEntity customerEntity;

        if (EmailValidator.getInstance().isValid(uid))
        {
            customerEntity = customerRepository.findByEmailIgnoreCase(uid).orElse(null);
        }
        else
        {
            customerEntity = customerRepository.findByAccountIgnoreCase(uid).orElse(null);
        }

        return Optional.ofNullable(customerEntity);
    }
}
