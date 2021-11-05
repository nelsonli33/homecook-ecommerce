package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.PaymentModeEntity;
import com.homecook.homecookstorefront.dto.PaymentModeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class PaymentModeMapper
{
    public abstract PaymentModeDTO convertToPaymentModeDTO(PaymentModeEntity paymentMode);

    public abstract List<PaymentModeDTO> convertToPaymentModeDTOs(List<PaymentModeEntity> paymentModes);
}
