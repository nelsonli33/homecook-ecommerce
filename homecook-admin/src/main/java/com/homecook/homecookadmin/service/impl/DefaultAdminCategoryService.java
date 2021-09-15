package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookcommon.util.ServicesUtil;
import com.homecook.homecookdomain.model.CategoryModel;
import com.homecook.homecookdomain.repository.CategoryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service(value = "adminCategoryService")
public class DefaultAdminCategoryService implements AdminCategoryService
{
    private CategoryRepository categoryRepository;

    @Autowired
    public DefaultAdminCategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryModel> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryModel> getAllCategoriesForIds(List<Long> ids)
    {
        List<CategoryModel> categoryModels = this.categoryRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(categoryModels)) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.MODELID_NOT_FOUND, "Category with ids: " + StringUtils.join(ids, ',') + " not found.");
        }
        return categoryModels;
    }

    @Override
    public CategoryModel getCategoryForId(Long id)
    {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryModel> categoryModel = categoryRepository.findById(id);
        if (!categoryModel.isPresent()) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.MODELID_NOT_FOUND, "Category with id "+id+" requested for the object does not exists in the system");
        }
        return categoryModel.get();
    }

    @Override
    public CategoryModel createCategory(CategoryModel categoryModel)
    {
        return null;
    }

    @Override
    public void addParentCategory(CategoryModel categoryModel, Long parentId)
    {
        CategoryModel parentCategory = null;
        if (parentId != null) {
            parentCategory = getCategoryForId(parentId);
            categoryModel.getChildren().add(parentCategory);
        }
        categoryModel.setParent(parentCategory);
        categoryRepository.save(categoryModel);
    }
}
