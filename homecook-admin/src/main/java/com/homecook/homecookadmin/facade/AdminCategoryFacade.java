package com.homecook.homecookadmin.facade;

import com.homecook.homecookadmin.dto.CategoryDTO;

import java.util.List;

public interface AdminCategoryFacade
{
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryDetail(Long id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
