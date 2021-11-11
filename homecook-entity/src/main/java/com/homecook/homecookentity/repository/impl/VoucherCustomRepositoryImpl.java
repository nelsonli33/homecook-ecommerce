package com.homecook.homecookentity.repository.impl;

import com.homecook.homecookentity.entity.QVoucherEntity;
import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.repository.VoucherCustomRepository;
import com.homecook.homecookentity.type.VoucherUseType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.List;

public class VoucherCustomRepositoryImpl implements VoucherCustomRepository
{
    private JPAQueryFactory queryFactory;

    public VoucherCustomRepositoryImpl(JPAQueryFactory queryFactory)
    {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<VoucherEntity> findSpecifiedCategoryUseTypeAndActiveVouchers()
    {
        QVoucherEntity voucher = QVoucherEntity.voucherEntity;

        return queryFactory.selectFrom(voucher).where(voucher.useType.eq(VoucherUseType.SPECIFIED_CATEGORY), voucher.endAt.after(new Date())).fetch();
    }

    @Override
    public boolean isValidVoucherCode(String voucherCode)
    {
        final Date beforeThirtyDays = DateUtils.addDays(new Date(), -30);
        QVoucherEntity voucher = QVoucherEntity.voucherEntity;

        final VoucherEntity voucherEntity = queryFactory.selectFrom(voucher).where(voucher.code.eq(voucherCode), voucher.endAt.after(beforeThirtyDays)).fetchOne();
        if (voucherEntity == null)
        {
            return true;
        }
        return false;
    }
}
