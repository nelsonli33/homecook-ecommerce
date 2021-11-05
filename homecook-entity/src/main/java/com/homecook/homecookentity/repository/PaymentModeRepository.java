package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.PaymentModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PaymentModeRepository extends JpaRepository<PaymentModeEntity, Long>, JpaSpecificationExecutor<PaymentModeEntity>
{
    List<PaymentModeEntity> findAllByActiveTrue();
}
