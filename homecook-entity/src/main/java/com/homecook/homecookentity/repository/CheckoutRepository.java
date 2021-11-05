package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends JpaRepository<CheckoutEntity, Long>
{
    CheckoutEntity findCheckoutEntityByCustomer(CustomerEntity customer);
}
