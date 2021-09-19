package com.homecook.homecookadmin.facade.converter;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookentity.entity.CategoryEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "adminCategoryReversePopulator")
public class AdminCategoryReversePopulator implements Populator<CategoryDTO, CategoryEntity>
{
    private AdminCategoryService adminCategoryService;

    public AdminCategoryReversePopulator(AdminCategoryService adminCategoryService)
    {
        this.adminCategoryService = adminCategoryService;
    }

    @Override
    public void populate(CategoryDTO source, CategoryEntity target)
    {
        Assert.notNull(source, "Parameter [CategoryEntity] cannot be null.");
        Assert.notNull(target, "Parameter [CategoryDTO] cannot be null.");

        target.setName(source.getName());
        target.setSortOrder(source.getSortOrder());

        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());

        addParentCategory(source, target);
    }

    private void addParentCategory(CategoryDTO source, CategoryEntity target) {
        if (source.getParentId() != null)
        {
            CategoryEntity parentCategory = adminCategoryService.getCategoryForId(source.getParentId());
            parentCategory.getChildren().add(parentCategory);
            target.setParent(parentCategory);
        }
    }
}
