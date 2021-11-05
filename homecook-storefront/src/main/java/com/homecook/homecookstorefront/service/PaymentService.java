package com.homecook.homecookstorefront.service;

import com.homecook.homecookentity.entity.PaymentModeEntity;

import java.util.List;

public interface PaymentService
{
    PaymentModeEntity getPaymentModeForCode(String code);

    List<PaymentModeEntity> getActivePaymentModes();
}
