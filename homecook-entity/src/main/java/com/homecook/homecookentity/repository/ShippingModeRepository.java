package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.ShippingModeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingModeRepository extends JpaRepository<ShippingModeEntity, Long>, JpaSpecificationExecutor<ShippingModeEntity>
{
    ShippingModeEntity findShippingModeEntityByCode(String code);

    List<ShippingModeEntity> findAllByActiveTrue();
}
