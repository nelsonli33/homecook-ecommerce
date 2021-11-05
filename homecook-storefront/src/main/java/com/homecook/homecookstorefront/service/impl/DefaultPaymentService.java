package com.homecook.homecookstorefront.service.impl;

import com.homecook.homecookentity.entity.PaymentModeEntity;
import com.homecook.homecookentity.repository.PaymentModeRepository;
import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "paymentModeService")
public class DefaultPaymentService implements PaymentService
{
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    public DefaultPaymentService(PaymentModeRepository paymentModeRepository)
    {
        this.paymentModeRepository = paymentModeRepository;
    }

    public PaymentModeEntity getPaymentModeForCode(String code)
    {
        Specification<PaymentModeEntity> sf = (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.equal(root.get("code"), code),
                criteriaBuilder.equal(root.get("active"), true)
        );

        final Optional<PaymentModeEntity> paymentMode = paymentModeRepository.findOne(sf);

        if (paymentMode.isPresent())
        {
            return paymentMode.get();
        }
        else
        {
            throw new StorefrontServerRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Payment mode with code " + code + " not found.");
        }
    }

    @Override
    public List<PaymentModeEntity> getActivePaymentModes()
    {
        return paymentModeRepository.findAllByActiveTrue();
    }
}
