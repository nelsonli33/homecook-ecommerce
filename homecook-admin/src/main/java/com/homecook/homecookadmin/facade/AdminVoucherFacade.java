package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.VoucherDTO;

import java.util.List;

public interface AdminVoucherFacade
{
    List<VoucherDTO> getAllVouchers();

    VoucherDTO getVoucherDetails(Long voucherId);

    VoucherDTO createVoucher(VoucherDTO voucherDTO);

    VoucherDTO updateVoucher(VoucherDTO voucherDTO);

    void deleteScheduledVoucher(Long voucherId);

    VoucherDTO endVoucher(Long voucherId);

}
