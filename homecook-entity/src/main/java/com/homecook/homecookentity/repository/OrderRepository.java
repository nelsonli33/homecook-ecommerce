package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>
{
    Optional<OrderEntity> findOrderEntityByCode(String orderCode);
}
