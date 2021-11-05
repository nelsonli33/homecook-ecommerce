package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.AddressEntity;
import com.homecook.homecookentity.entity.ShippingAddressEntity;
import com.homecook.homecookstorefront.dto.AddressDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public abstract class AddressMapper
{
    public abstract AddressDTO convertToAddressDTO(AddressEntity addressEntity);

    public abstract AddressDTO convertToAddressDTO(ShippingAddressEntity shippingAddressEntity);
}
