package com.homecook.homecookadmin.facade.mapper;


import com.homecook.homecookadmin.dto.VoucherDTO;
import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookadmin.service.AdminProductService;
import com.homecook.homecookadmin.service.AdminVoucherService;
import com.homecook.homecookentity.entity.CategoryEntity;
import com.homecook.homecookentity.entity.ProductEntity;
import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.type.VoucherStatusType;
import com.homecook.homecookentity.type.VoucherUseType;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {AdminCategoryMapper.class, AdminProductMapper.class}
)
public abstract class AdminVoucherMapper
{
    private AdminCategoryService adminCategoryService;
    private AdminProductService adminProductService;
    private AdminVoucherService adminVoucherService;

    public abstract VoucherEntity convertToVoucherEntity(VoucherDTO voucherDTO);


    @InheritConfiguration(name = "convertToVoucherEntity")
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "code", ignore = true),
            @Mapping(target = "useType", ignore = true),
    })
    public abstract void updateScheduledVoucherEntityFromDTO(VoucherDTO voucherDTO, @MappingTarget VoucherEntity voucherEntity);


    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "endAt", target = "endAt"),
            @Mapping(source = "usageCount", target = "usageCount"),
    })
    public abstract void updateRunningVoucherEntityFromDTO(VoucherDTO voucherDTO, @MappingTarget VoucherEntity voucherEntity);

    @AfterMapping
    public void appliedCategories(VoucherDTO voucherDTO, @MappingTarget VoucherEntity voucherEntity)
    {
        if (VoucherUseType.SPECIFIED_CATEGORY.equals(voucherDTO.getUseType()))
        {
            final List<CategoryEntity> categories = getAdminCategoryService().getCategoriesForIds(voucherDTO.getAppliedCategoryIds());

            for (CategoryEntity category : categories)
            {
                final VoucherEntity activeVoucher = getAdminCategoryService().getActiveVoucherForCategory(category);
                if (activeVoucher != null && !activeVoucher.equals(voucherEntity))
                {
                    throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_APPLIED_TO_CATEGORY_ERROR, "Category Id: " + category.getId() + " has been used by voucher [code=" + activeVoucher.getCode() + "] event.");
                }
            }

            voucherEntity.setAppliedCategories(new HashSet<>(categories));
        }
    }

    @AfterMapping
    public void appliedProducts(VoucherDTO voucherDTO, @MappingTarget VoucherEntity voucherEntity)
    {
        if (VoucherUseType.SPECIFIED_PRODUCT.equals(voucherDTO.getUseType()))
        {
            final List<ProductEntity> products
                    = getAdminProductService().getAvailableProductsForIds(voucherDTO.getAppliedProductIds());

            for (ProductEntity product : products)
            {
                final VoucherEntity activeVoucher = getAdminProductService().getActiveVoucherForProduct(product);
                if (activeVoucher != null && !activeVoucher.equals(voucherEntity))
                {
                    throw new HomecookAdminRuntimeException(InternalErrorCode.VOUCHER_APPLIED_TO_PRODUCT_ERROR, "Product Id: " + product.getId() + " has been used by voucher [code=" + activeVoucher.getCode() + "] event.");
                }
            }
            voucherEntity.setAppliedProducts(new HashSet<>(products));
        }
    }


    public abstract VoucherDTO convertToVoucherDTO(VoucherEntity voucherEntity);

    public abstract List<VoucherDTO> convertToVoucherDTOs(List<VoucherEntity> voucherEntities);

    @AfterMapping
    public void addStatus(VoucherEntity voucherEntity, @MappingTarget VoucherDTO voucherDTO)
    {
        VoucherStatusType status = getAdminVoucherService().getStatusForVoucher(voucherEntity);
        voucherDTO.setStatus(status);
    }


    public AdminCategoryService getAdminCategoryService()
    {
        return adminCategoryService;
    }

    @Autowired
    public void setAdminCategoryService(AdminCategoryService adminCategoryService)
    {
        this.adminCategoryService = adminCategoryService;
    }

    public AdminVoucherService getAdminVoucherService()
    {
        return adminVoucherService;
    }

    @Autowired
    public void setAdminVoucherService(AdminVoucherService adminVoucherService)
    {
        this.adminVoucherService = adminVoucherService;
    }

    public AdminProductService getAdminProductService()
    {
        return adminProductService;
    }

    @Autowired
    public void setAdminProductService(AdminProductService adminProductService)
    {
        this.adminProductService = adminProductService;
    }
}
