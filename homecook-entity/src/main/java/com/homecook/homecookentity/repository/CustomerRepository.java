package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>
{
    Optional<CustomerEntity> findByAccountIgnoreCase(String account);

    Optional<CustomerEntity> findByEmailIgnoreCase(String email);

    boolean existsByAccountIgnoreCase(String account);

    boolean existsByEmailIgnoreCase(String email);
}
