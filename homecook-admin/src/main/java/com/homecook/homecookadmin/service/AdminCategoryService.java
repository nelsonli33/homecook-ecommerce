package com.homecook.homecookadmin.service;


import com.homecook.homecookentity.entity.CategoryEntity;
import com.homecook.homecookentity.entity.VoucherEntity;

import java.util.List;

public interface AdminCategoryService
{
    List<CategoryEntity> getCategories();

    List<CategoryEntity> getCategoriesForIds(List<Long> ids);

    CategoryEntity getCategoryForId(Long id);

    void addParentCategory(CategoryEntity categoryEntity, Long parentId);

    VoucherEntity getActiveVoucherForCategory(CategoryEntity categoryEntity);
}
