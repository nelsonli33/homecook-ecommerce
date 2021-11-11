package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminVoucherService;
import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.repository.VoucherRepository;
import com.homecook.homecookentity.service.ModelService;
import com.homecook.homecookentity.type.VoucherStatusType;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = "adminVoucherService")
public class DefaultAdminVoucherService implements AdminVoucherService
{
    private ModelService modelService;
    private VoucherRepository voucherRepository;

    @Autowired
    public DefaultAdminVoucherService(ModelService modelService, VoucherRepository voucherRepository)
    {
        this.modelService = modelService;
        this.voucherRepository = voucherRepository;
    }

    @Override
    public List<VoucherEntity> getVouchers()
    {
        return getVoucherRepository().findAll();
    }

    @Override
    public VoucherEntity getVoucherForId(Long id)
    {
        final Optional<VoucherEntity> voucher = getVoucherRepository().findById(id);
        if (!voucher.isPresent())
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Voucher with id " + id + " requested for the object does not exists in the system");
        }
        return voucher.get();
    }

    @Override
    public VoucherEntity saveVoucher(VoucherEntity voucherEntity)
    {
        validateVoucherIsValid(voucherEntity);

        voucherEntity.setReceiveCount(0);
        voucherEntity.setCurrentUsageCount(0);
        voucherEntity.setDisplay(voucherEntity.getDisplay() == null || voucherEntity.getDisplay());
        getModelService().save(voucherEntity);

        return voucherEntity;
    }


    @Override
    public VoucherStatusType getStatusForVoucher(VoucherEntity voucherEntity)
    {
        Date now = new Date();

        VoucherStatusType status = null;

        if (now.before(voucherEntity.getStartAt()))
        {
            status = VoucherStatusType.SCHEDULED;
        }
        else if (now.after(voucherEntity.getStartAt()) && now.before(voucherEntity.getEndAt()))
        {
            status = VoucherStatusType.RUNNING;
        }
        else if (now.after(voucherEntity.getEndAt()))
        {
            status = VoucherStatusType.ENDED;
        }

        return status;
    }

    @Override
    public void deleteScheduledVoucher(VoucherEntity voucherEntity)
    {
        final VoucherStatusType currentStatus = getStatusForVoucher(voucherEntity);

        if (VoucherStatusType.SCHEDULED.equals(currentStatus))
        {
//            switch (voucherEntity.getUseType())
//            {
//                case SPECIFIED_CATEGORY:
//                    final List<CategoryEntity> appliedCategories
//                            = voucherEntity.getAppliedCategories().stream().peek(cat -> cat.setVoucher(null)).collect(Collectors.toList());
//                    getModelService().saveAll(appliedCategories);
//                    break;
//            }

            getModelService().remove(voucherEntity);
        }
    }

    @Override
    public VoucherEntity endVoucher(VoucherEntity voucherEntity)
    {
        final VoucherStatusType currentStatus = getStatusForVoucher(voucherEntity);

        if (VoucherStatusType.RUNNING.equals(currentStatus))
        {
            voucherEntity.setEndAt(new Date());
            getModelService().save(voucherEntity);
        }

        return voucherEntity;
    }


    private void validateVoucherIsValid(VoucherEntity voucherEntity)
    {
        validateVoucherCode(voucherEntity);
        validateVoucherTimeline(voucherEntity);
        validateVoucherUseType(voucherEntity);
    }

    private void validateVoucherCode(VoucherEntity voucherEntity)
    {
        if (!getVoucherRepository().isValidVoucherCode(voucherEntity.getCode()))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_CODE_ALREADY_IN_USE, "Voucher code already in use.");
        }
    }

    private void validateVoucherTimeline(VoucherEntity voucherEntity)
    {
        Date now = new Date();
        if (voucherEntity.getStartAt() != null && !voucherEntity.getStartAt().after(now))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_START_TIME_ERROR, "Voucher start time must be after now. [now: " + now + " ]");
        }

        if (voucherEntity.getStartAt() != null && voucherEntity.getEndAt() != null && voucherEntity.getStartAt().after(voucherEntity.getEndAt()))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_START_TIME_AFTER_END_TIME_ERROR, "Voucher start time must before end time.");
        }

        if (!voucherEntity.getEndAt().after(DateUtils.addHours(voucherEntity.getStartAt(), 1)))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_END_TIME_ERROR, "Voucher end time at least one hour later than start time.");
        }

        if (voucherEntity.getEndAt().after(DateUtils.addMonths(now, 3)))
        {
            throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_END_TIME_ERROR, "Voucher end time at most three months later than now.");
        }
    }

    private void validateVoucherUseType(VoucherEntity voucherEntity)
    {
        switch (voucherEntity.getUseType())
        {
            case SPECIFIED_CATEGORY:
                if (voucherEntity.getAppliedCategories().size() == 0)
                {
                    throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_APPLIED_TO_CATEGORY_ERROR, "Applied Category can not be empty.");
                }
                break;
            case SPECIFIED_PRODUCT:
                if (voucherEntity.getAppliedProducts().size() == 0)
                {
                    throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_APPLIED_TO_PRODUCT_ERROR, "Applied Product can not be empty.");
                }
        }
    }


    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }

    public VoucherRepository getVoucherRepository()
    {
        return voucherRepository;
    }

    public void setVoucherRepository(VoucherRepository voucherRepository)
    {
        this.voucherRepository = voucherRepository;
    }
}
