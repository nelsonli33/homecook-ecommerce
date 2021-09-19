package com.homecook.homecookadmin.facade.impl;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.facade.AdminCategoryFacade;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookentity.entity.CategoryEntity;
import com.homecook.homecookentity.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "adminCategoryFacade")
public class DefaultAdminCategoryFacade implements AdminCategoryFacade
{
    private AdminCategoryService adminCategoryService;
    private ModelService modelService;
    private Converter<CategoryEntity, CategoryDTO> adminCategoryConverter;
    private Populator<CategoryDTO, CategoryEntity> adminCategoryReversePopulator;

    @Autowired
    public DefaultAdminCategoryFacade(
            @Qualifier(value = "adminCategoryService") AdminCategoryService adminCategoryService,
            @Qualifier(value = "modelService") ModelService modelService,
            @Qualifier(value = "adminCategoryConverter") Converter<CategoryEntity, CategoryDTO> adminCategoryConverter,
            @Qualifier(value = "adminCategoryReversePopulator") Populator<CategoryDTO, CategoryEntity> adminCategoryReversePopulator
    )
    {
        this.adminCategoryService = adminCategoryService;
        this.modelService = modelService;
        this.adminCategoryConverter = adminCategoryConverter;
        this.adminCategoryReversePopulator = adminCategoryReversePopulator;
    }

    @Override
    public List<CategoryDTO> getAllCategories()
    {
        List<CategoryEntity> allCategories = adminCategoryService.listCategories();
        return adminCategoryConverter.convertAll(allCategories);
    }

    @Override
    public CategoryDTO getCategoryForId(Long id)
    {
        return adminCategoryConverter.convert(adminCategoryService.getCategoryForId(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        CategoryEntity categoryEntity = new CategoryEntity();
        adminCategoryReversePopulator.populate(categoryDTO, categoryEntity);
        getModelService().save(categoryEntity);
        return adminCategoryConverter.convert(categoryEntity);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO)
    {
        CategoryEntity categoryEntity = adminCategoryService.getCategoryForId(id);
        adminCategoryReversePopulator.populate(categoryDTO, categoryEntity);
        getModelService().save(categoryEntity);
        return adminCategoryConverter.convert(categoryEntity);
    }

    @Override
    public void deleteCategory(Long id)
    {
        CategoryEntity categoryEntity = adminCategoryService.getCategoryForId(id);
        getModelService().remove(categoryEntity);
    }



    public AdminCategoryService getAdminCategoryService()
    {
        return adminCategoryService;
    }

    public void setAdminCategoryService(AdminCategoryService adminCategoryService)
    {
        this.adminCategoryService = adminCategoryService;
    }

    public ModelService getModelService()
    {
        return modelService;
    }

    public void setModelService(ModelService modelService)
    {
        this.modelService = modelService;
    }
}
