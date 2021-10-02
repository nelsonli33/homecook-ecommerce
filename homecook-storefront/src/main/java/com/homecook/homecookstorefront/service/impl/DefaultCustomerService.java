package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.repository.CustomerRepository;
import com.homecook.homecookstorefront.service.CustomerService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
