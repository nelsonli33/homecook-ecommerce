package com.homecook.homecookadmin.service;

import com.homecook.homecookdomain.model.CategoryModel;

import java.util.List;

public interface AdminCategoryService
{
    List<CategoryModel> getAllCategories();

    List<CategoryModel> getAllCategoriesForIds(List<Long> ids);

    CategoryModel getCategoryForId(Long id);

    CategoryModel createCategory(CategoryModel categoryModel);

    void addParentCategory(CategoryModel categoryModel, Long parentId);
}
