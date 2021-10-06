package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.CartEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>
{
    CartEntity findCartEntityByCustomer(CustomerEntity customer);
}
