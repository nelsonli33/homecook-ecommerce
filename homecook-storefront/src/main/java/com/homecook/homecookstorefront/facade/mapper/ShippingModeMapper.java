package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.ShippingModeEntity;
import com.homecook.homecookstorefront.dto.ShippingModeDTO;
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
public abstract class ShippingModeMapper
{
    public abstract ShippingModeDTO convertToShippingModeDTO(ShippingModeEntity shippingMode);

    public abstract List<ShippingModeDTO> convertToShippingModeDTOs(List<ShippingModeEntity> shippingModes);
}
