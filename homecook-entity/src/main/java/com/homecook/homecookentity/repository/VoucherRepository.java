package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Long>, VoucherCustomRepository
{

}
