package com.homecook.homecookadmin.facade.impl;

import com.homecook.homecookadmin.dto.VoucherDTO;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.facade.AdminVoucherFacade;
import com.homecook.homecookadmin.facade.mapper.AdminVoucherMapper;
import com.homecook.homecookadmin.service.AdminVoucherService;
import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.type.VoucherStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "adminVoucherFacade")
public class DefaultAdminVoucherFacade implements AdminVoucherFacade
{
    private AdminVoucherMapper adminVoucherMapper;
    private AdminVoucherService adminVoucherService;

    @Autowired
    public DefaultAdminVoucherFacade(AdminVoucherMapper adminVoucherMapper, AdminVoucherService adminVoucherService)
    {
        this.adminVoucherMapper = adminVoucherMapper;
        this.adminVoucherService = adminVoucherService;
    }

    @Override
    public List<VoucherDTO> getAllVouchers()
    {
        final List<VoucherEntity> vouchers = getAdminVoucherService().getVouchers();
        return getAdminVoucherMapper().convertToVoucherDTOs(vouchers);
    }

    @Override
    public VoucherDTO getVoucherDetails(Long voucherId)
    {
        final VoucherEntity voucherEntity = getAdminVoucherService().getVoucherForId(voucherId);
        return getAdminVoucherMapper().convertToVoucherDTO(voucherEntity);
    }


    @Override
    public VoucherDTO createVoucher(VoucherDTO voucherDTO)
    {
        final VoucherEntity voucherEntity
                = getAdminVoucherMapper().convertToVoucherEntity(voucherDTO);
        final VoucherEntity voucher
                = getAdminVoucherService().saveVoucher(voucherEntity);
        return getAdminVoucherMapper().convertToVoucherDTO(voucher);
    }

    @Override
    public VoucherDTO updateVoucher(VoucherDTO voucherDTO)
    {
        final VoucherEntity voucher = getAdminVoucherService().getVoucherForId(voucherDTO.getId());
        final VoucherStatusType status = getAdminVoucherService().getStatusForVoucher(voucher);

        switch (status)
        {
            case SCHEDULED:
                getAdminVoucherMapper().updateScheduledVoucherEntityFromDTO(voucherDTO, voucher);
                getAdminVoucherService().saveVoucher(voucher);
                break;
            case RUNNING:
                getAdminVoucherMapper().updateRunningVoucherEntityFromDTO(voucherDTO, voucher);
                getAdminVoucherService().saveVoucher(voucher);
                break;
            case ENDED:
                throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_MODIFIER_ERROR,
                        "Could not modify voucher info because voucher with code:[" + voucher.getCode() + "] already ended.");
        }

        return getAdminVoucherMapper().convertToVoucherDTO(voucher);
    }

    @Override
    public void deleteScheduledVoucher(Long voucherId)
    {
        final VoucherEntity voucher = getAdminVoucherService().getVoucherForId(voucherId);
        getAdminVoucherService().deleteScheduledVoucher(voucher);
    }

    @Override
    public VoucherDTO endVoucher(Long voucherId)
    {
        final VoucherEntity voucher = getAdminVoucherService().getVoucherForId(voucherId);
        getAdminVoucherService().endVoucher(voucher);

        return getAdminVoucherMapper().convertToVoucherDTO(voucher);
    }

    public AdminVoucherMapper getAdminVoucherMapper()
    {
        return adminVoucherMapper;
    }

    public void setAdminVoucherMapper(AdminVoucherMapper adminVoucherMapper)
    {
        this.adminVoucherMapper = adminVoucherMapper;
    }

    public AdminVoucherService getAdminVoucherService()
    {
        return adminVoucherService;
    }

    public void setAdminVoucherService(AdminVoucherService adminVoucherService)
    {
        this.adminVoucherService = adminVoucherService;
    }
}
