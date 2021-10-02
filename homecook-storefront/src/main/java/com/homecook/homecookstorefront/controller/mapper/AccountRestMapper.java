package com.homecook.homecookstorefront.controller.mapper;

import com.homecook.homecookstorefront.dto.CustomerDTO;
import com.homecook.homecookstorefront.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface AccountRestMapper
{
    Customer convertDTOtoResponse(CustomerDTO customerDTO);
}
