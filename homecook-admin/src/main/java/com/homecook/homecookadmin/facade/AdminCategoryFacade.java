package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.CategoryData;

import java.util.List;

public interface AdminCategoryFacade
{
    List<CategoryData> getAllCategories();

    CategoryData getCategoryForId(Long id);

    CategoryData createCategory(CategoryData categoryData);

    CategoryData updateCategory(Long id, CategoryData categoryData);

    void deleteCategory(Long id);

    void orderingCategories(List<CategoryData> categoryDataList);
}
