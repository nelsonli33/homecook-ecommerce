package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookentity.type.InvoiceType;
import com.homecook.homecookstorefront.dto.CheckoutDTO;
import com.homecook.homecookstorefront.dto.CheckoutInvoiceDTO;
import com.homecook.homecookstorefront.dto.PaymentModeDTO;
import com.homecook.homecookstorefront.dto.ShippingModeDTO;
import com.homecook.homecookstorefront.model.Checkout;
import com.homecook.homecookstorefront.model.CheckoutInvoice;
import com.homecook.homecookstorefront.model.PaymentMode;
import com.homecook.homecookstorefront.model.ShippingMode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {AccountRestMapper.class, CartRestMapper.class, CustomerRestMapper.class}
)
public interface CheckoutRestMapper
{

    Checkout toCheckout(CheckoutDTO checkoutDTO);

    PaymentMode toPaymentMode(PaymentModeDTO paymentModeDTO);

    ShippingMode toShippingMode(ShippingModeDTO paymentModeDTO);

    @Mapping(source = "invoiceType", target = "invoiceType", qualifiedByName = "invoiceTypeCodeConverter")
    CheckoutInvoiceDTO toCheckoutInvoiceDTO(CheckoutInvoice checkoutInvoice);

    @Named("invoiceTypeCodeConverter")
    default InvoiceType invoiceTypeCodeConverter(String invoiceType)
    {
        return InvoiceType.fromCode(invoiceType);
    }
}
