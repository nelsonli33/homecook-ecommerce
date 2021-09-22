package com.homecook.homecookadmin.service;


import com.homecook.homecookentity.entity.CategoryEntity;

import java.util.List;

public interface AdminCategoryService
{
    List<CategoryEntity> getCategories();

    List<CategoryEntity> getCategoriesForIds(List<Long> ids);

    CategoryEntity getCategoryForId(Long id);

    void addParentCategory(CategoryEntity entity, Long parentId);
}
