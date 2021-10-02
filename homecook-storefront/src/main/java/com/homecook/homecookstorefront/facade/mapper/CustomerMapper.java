package com.homecook.homecookstorefront.facade.mapper;

import com.homecook.homecookentity.entity.CustomerEntity;
import com.homecook.homecookstorefront.dto.CustomerDTO;
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
public abstract class CustomerMapper
{
    public abstract CustomerDTO convertToCustomerDTO(CustomerEntity entity);

}
