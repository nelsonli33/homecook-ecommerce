package com.homecook.homecookadmin.controller.mapper;

import com.homecook.homecookadmin.dto.VoucherDTO;
import com.homecook.homecookadmin.model.CreateVoucherRequest;
import com.homecook.homecookadmin.model.UpdateVoucherRequest;
import com.homecook.homecookadmin.model.Voucher;
import com.homecook.homecookcommon.mapper.DateMapper;
import com.homecook.homecookentity.type.VoucherStatusType;
import com.homecook.homecookentity.type.VoucherUseType;
import com.homecook.homecookentity.type.VoucherValueType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        uses = {DateMapper.class, CategoryRestMapper.class}
)
public interface VoucherRestMapper
{

    VoucherDTO convertRequestToDTO(CreateVoucherRequest request);

    VoucherDTO convertRequestToDTO(UpdateVoucherRequest request);

    @Mappings({
            @Mapping(target = "startAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"),
            @Mapping(target = "endAt", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"),
    })
    Voucher convertDTOtoResponse(VoucherDTO voucherDTO);

    List<Voucher> convertAllDTOtoResponse(List<VoucherDTO> voucherDTOs);


    default VoucherUseType useTypeConverter(int useType)
    {
        return VoucherUseType.valueOf(useType);
    }

    default Integer useTypeConverter(VoucherUseType useType)
    {
        if (useType != null)
        {
            return useType.getValue();
        }
        return null;
    }

    default VoucherValueType valueTypeConverter(int valueType)
    {
        return VoucherValueType.valueOf(valueType);
    }

    default Integer valueTypeConverter(VoucherValueType valueType)
    {
        if (valueType != null)
        {
            return valueType.getValue();
        }
        return null;
    }

    default Integer statusTypeConverter(VoucherStatusType statusType)
    {
        if (statusType != null)
        {
            return statusType.getValue();
        }
        return null;
    }

}
