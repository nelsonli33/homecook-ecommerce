package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookadmin.service.AdminVoucherService;
import com.homecook.homecookcommon.util.ServicesUtil;
import com.homecook.homecookentity.entity.CategoryEntity;
import com.homecook.homecookentity.entity.VoucherEntity;
import com.homecook.homecookentity.repository.CategoryRepository;
import com.homecook.homecookentity.type.VoucherStatusType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service(value = "adminCategoryService")
public class DefaultAdminCategoryService extends AbstractBaseService implements AdminCategoryService
{
    private CategoryRepository categoryRepository;
    private AdminVoucherService adminVoucherService;

    @Autowired
    public DefaultAdminCategoryService(
            CategoryRepository categoryRepository,
            AdminVoucherService adminVoucherService
    )
    {
        this.categoryRepository = categoryRepository;
        this.adminVoucherService = adminVoucherService;
    }

    @Override
    public List<CategoryEntity> getCategories()
    {
        return getCategoryRepository().findAll();
    }

    @Override
    public List<CategoryEntity> getCategoriesForIds(List<Long> ids)
    {
        List<CategoryEntity> categoryEntities = getCategoryRepository().findAllById(ids);
        if (CollectionUtils.isEmpty(categoryEntities)) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Category with ids: " + StringUtils.join(ids, ',') + " not found.");
        }
        return categoryEntities;
    }

    @Override
    public CategoryEntity getCategoryForId(Long id)
    {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryEntity> categoryModel = getCategoryRepository().findById(id);
        if (!categoryModel.isPresent()) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Category with id "+id+" requested for the object does not exists in the system");
        }
        return categoryModel.get();
    }

    @Override
    public void addParentCategory(CategoryEntity categoryEntity, Long parentId)
    {
        CategoryEntity parentCategory = null;
        if (parentId != null)
        {
            parentCategory = getCategoryForId(parentId);
            categoryEntity.getChildren().add(parentCategory);
        }
        categoryEntity.setParent(parentCategory);
        getCategoryRepository().save(categoryEntity);
    }

    @Override
    public VoucherEntity getActiveVoucherForCategory(CategoryEntity categoryEntity)
    {
        for (final VoucherEntity voucherEntity : categoryEntity.getVouchers())
        {
            final VoucherStatusType status = getAdminVoucherService().getStatusForVoucher(voucherEntity);
            if (VoucherStatusType.SCHEDULED.equals(status) || VoucherStatusType.RUNNING.equals(status))
            {
                return voucherEntity;
            }
        }
        return null;
    }

    public CategoryRepository getCategoryRepository()
    {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public AdminVoucherService getAdminVoucherService()
    {
        return adminVoucherService;
    }

    public void setAdminVoucherService(AdminVoucherService adminVoucherService)
    {
        this.adminVoucherService = adminVoucherService;
    }
}
