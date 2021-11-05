package com.homecook.homecookstorefront.facade;

import com.homecook.tappay.models.BackendNotifyRequest;

public interface PaymentFacade
{
    String beginPayment(String prime, String orderCode);

    void authorizePayment(BackendNotifyRequest request);
}
