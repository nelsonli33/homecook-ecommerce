package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerService
{
    Optional<CustomerEntity> getCustomerForUid(String uid);
}
