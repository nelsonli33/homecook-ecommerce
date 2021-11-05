package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookentity.entity.InvoiceSettingEntity;
import com.homecook.homecookentity.entity.ShippingModeEntity;
import com.homecook.homecookstorefront.dto.*;
import com.homecook.homecookstorefront.service.ShippingService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ShippingModeMapper.class}
)
public abstract class CustomerMapper
{
    private ShippingService shippingService;

    public abstract CustomerDTO convertToCustomerDTO(CustomerEntity entity);

    @Mappings({
            @Mapping(source = "shippingModeCode", target = "shippingMode", qualifiedByName = "toShippingMode")
    })
    public abstract AddressEntity convertTAddressEntity(AddressDTO addressDTO);

    @Mappings({
            @Mapping(target = "shippingMode", ignore = true)
    })
    public abstract void updateAddressEntity(AddressDTO addressDTO, @MappingTarget AddressEntity addressEntity);

    public abstract AddressDTO convertToAddressDTO(AddressEntity addressEntity);

    public abstract List<AddressDTO> convertToAddressDTOs(List<AddressEntity> addressEntities);

    @Named(value = "toShippingMode")
    public ShippingModeEntity toShippingMode(String code)
    {
        return getShippingModeService().getShippingModeForCode(code);
    }


    @Mappings({
            @Mapping(target = "invoiceType", expression = "java(com.homecook.homecookentity.type.InvoiceType.COMPANY)"),
            @Mapping(source = "email", target = "contactEmail"),
            @Mapping(source = "companyName", target = "invoiceTitle")
    })
    public abstract InvoiceSettingEntity convertToInvoiceSettingEntity(CompanyInvoiceDTO companyInvoiceDTO);

    @InheritConfiguration
    public abstract void updateInvoiceSettingEntity(CompanyInvoiceDTO companyInvoiceDTO, @MappingTarget InvoiceSettingEntity invoiceSettingEntity);

    @Mappings({
            @Mapping(target = "invoiceType", expression = "java(com.homecook.homecookentity.type.InvoiceType.DONATION)")
    })
    public abstract InvoiceSettingEntity convertToInvoiceSettingEntity(DonationInvoiceDTO donationInvoiceDTO);

    @InheritConfiguration
    public abstract void updateInvoiceSettingEntity(DonationInvoiceDTO donationInvoiceDTO, @MappingTarget InvoiceSettingEntity invoiceSettingEntity);

    @Mappings({
            @Mapping(source = "defaultAddress", target = "address")
    })
    public abstract PersonalInvoiceDTO convertToPersonalInvoiceDTO(CustomerEntity customerEntity);

    @Mappings({
            @Mapping(source = "contactEmail", target = "email"),
            @Mapping(source = "invoiceTitle", target = "companyName")
    })
    public abstract CompanyInvoiceDTO convertToCompanyInvoiceDTO(InvoiceSettingEntity invoiceSettingEntity);

    public abstract DonationInvoiceDTO convertToDonationInvoiceDTO(InvoiceSettingEntity invoiceSettingEntity);


    public ShippingService getShippingModeService()
    {
        return shippingService;
    }

    @Autowired
    public void setShippingModeService(ShippingService shippingService)
    {
        this.shippingService = shippingService;
    }
}
