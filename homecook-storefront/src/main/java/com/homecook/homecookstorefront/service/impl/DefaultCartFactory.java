package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookcommon.service.impl.UUIDKeyGenerator;
import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookstorefront.service.CartFactory;
import com.homecook.homecookstorefront.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "cartFactory")
public class DefaultCartFactory implements CartFactory
{
    private CustomerService customerService;
    private ModelService modelService;
    private UUIDKeyGenerator uuidKeyGenerator;

    @Autowired
    public DefaultCartFactory(CustomerService customerService, ModelService modelService, UUIDKeyGenerator uuidKeyGenerator)
    {
        this.customerService = customerService;
        this.modelService = modelService;
        this.uuidKeyGenerator = uuidKeyGenerator;
    }

    @Override
    public CartEntity createCart()
    {
        CustomerEntity currentCustomer = customerService.getCurrentCustomer();
        CartEntity cart = new CartEntity();
        cart.setCode(uuidKeyGenerator.generate().toString());
        cart.setCustomer(currentCustomer);
        modelService.save(cart);
        return cart;
    }
}
