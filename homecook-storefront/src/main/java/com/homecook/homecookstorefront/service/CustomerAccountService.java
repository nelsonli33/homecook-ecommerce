package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CustomerEntity;

public interface CustomerAccountService
{
    CustomerEntity register(CustomerEntity customerEntity, String password);
}
