package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.CheckoutEntity;
import com.homecook.homecookentity.entity.CheckoutInvoiceEntity;
import com.homecook.homecookstorefront.dto.CheckoutDTO;
import com.homecook.homecookstorefront.dto.CheckoutInvoiceDTO;
import com.homecook.homecookstorefront.dto.CompanyInvoiceDTO;
import com.homecook.homecookstorefront.dto.DonationInvoiceDTO;
import org.mapstruct.*;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {CartMapper.class, PaymentModeMapper.class, CustomerMapper.class}
)
public abstract class CheckoutMapper
{
    @Mappings({
            @Mapping(source = "paymentMode", target = "selectedPaymentMethod"),
    })
    public abstract CheckoutDTO convertToCheckoutDTO(CheckoutEntity checkout);

    public abstract CheckoutInvoiceEntity convertToCheckoutInvoiceEntity(CheckoutInvoiceDTO checkoutInvoiceDTO);

    @Mapping(source = "contactEmail", target = "email")
    public abstract CompanyInvoiceDTO convertToCompanyInvoiceDTO(CheckoutInvoiceDTO checkoutInvoiceDTO);

    public abstract DonationInvoiceDTO convertToDonationInvoiceDTO(CheckoutInvoiceDTO checkoutInvoiceDTO);
}
