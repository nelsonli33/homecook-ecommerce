package com.homecook.homecookadmin.service;

import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.type.VoucherStatusType;

import java.util.List;

public interface AdminVoucherService
{
    List<VoucherEntity> getVouchers();

    VoucherEntity getVoucherForId(Long id);

    VoucherEntity saveVoucher(VoucherEntity voucherEntity);

    VoucherStatusType getStatusForVoucher(VoucherEntity voucherEntity);

    void deleteScheduledVoucher(VoucherEntity voucherEntity);

    VoucherEntity endVoucher(VoucherEntity voucherEntity);
}
