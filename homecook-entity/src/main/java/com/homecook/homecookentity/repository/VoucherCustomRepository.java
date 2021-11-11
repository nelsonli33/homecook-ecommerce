package com.homecook.homecookentity.repository;

import com.homecook.homecookentity.entity.VoucherEntity;

import java.util.List;

public interface VoucherCustomRepository
{
    List<VoucherEntity> findSpecifiedCategoryUseTypeAndActiveVouchers();

    boolean isValidVoucherCode(String voucherCode);
}
