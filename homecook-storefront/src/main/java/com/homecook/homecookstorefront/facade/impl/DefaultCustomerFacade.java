package com.homecook.homecookstorefront.facade.impl;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookstorefront.dto.CustomerDTO;
import com.homecook.homecookstorefront.dto.RegisterDTO;
import com.homecook.homecookstorefront.facade.CustomerFacade;
import com.homecook.homecookstorefront.facade.mapper.CustomerMapper;
import com.homecook.homecookstorefront.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static com.homecook.homecookcommon.util.ServicesUtil.validateParameterNotNullStandardMessage;

@Component(value = "customerFacade")
public class DefaultCustomerFacade implements CustomerFacade
{
    private CustomerAccountService customerAccountService;
    private CustomerMapper customerMapper;

    @Autowired
    public DefaultCustomerFacade(
            CustomerAccountService customerAccountService,
            CustomerMapper customerMapper
    )
    {
        this.customerAccountService = customerAccountService;
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

        final CustomerEntity registeredCustomer = customerAccountService.register(newCustomer, registerDTO.getPassword());
        return customerMapper.convertToCustomerDTO(registeredCustomer);
    }
}
