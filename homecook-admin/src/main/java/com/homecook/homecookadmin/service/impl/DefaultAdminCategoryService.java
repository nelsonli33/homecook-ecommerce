package com.homecook.homecookadmin.service.impl;

import com.homecook.homecookadmin.error.InternalErrorCode;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookcommon.util.ServicesUtil;
import com.homecook.homecookentity.entity.CategoryEntity;
import com.homecook.homecookentity.repository.CategoryRepository;
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
    public List<CategoryEntity> listCategories()
    {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryEntity> listCategoriesForIds(List<Long> ids)
    {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(categoryEntities)) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Category with ids: " + StringUtils.join(ids, ',') + " not found.");
        }
        return categoryEntities;
    }

    @Override
    public CategoryEntity getCategoryForId(Long id)
    {
        ServicesUtil.validateParameterNotNullStandardMessage("id", id);
        Optional<CategoryEntity> categoryModel = categoryRepository.findById(id);
        if (!categoryModel.isPresent()) {
            throw new HomecookAdminRuntimeException(InternalErrorCode.ENTITY_NOT_FOUND, "Category with id "+id+" requested for the object does not exists in the system");
        }
        return categoryModel.get();
    }

    @Override
    public void addParentCategory(CategoryEntity categoryEntity, Long parentId)
    {
        CategoryEntity parentCategory = null;
        if (parentId != null) {
            parentCategory = getCategoryForId(parentId);
            categoryEntity.getChildren().add(parentCategory);
        }
        categoryEntity.setParent(parentCategory);
        categoryRepository.save(categoryEntity);
    }
}
