package com.homecook.homecookadmin.facade.impl;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookadmin.facade.AdminCategoryFacade;
import com.homecook.homecookadmin.facade.mapper.AdminCategoryMapper;
import com.homecook.homecookadmin.service.AdminCategoryService;
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
    private AdminCategoryMapper adminCategoryMapper;

    @Autowired
    public DefaultAdminCategoryFacade(
            @Qualifier(value = "adminCategoryService") AdminCategoryService adminCategoryService,
            @Qualifier(value = "modelService") ModelService modelService,
            @Autowired AdminCategoryMapper adminCategoryMapper
    )
    {
        this.adminCategoryService = adminCategoryService;
        this.modelService = modelService;
        this.adminCategoryMapper = adminCategoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories()
    {
        List<CategoryEntity> allCategories = adminCategoryService.getCategories();
        return adminCategoryMapper.convertAllToDTO(allCategories);
    }

    @Override
    public CategoryDTO getCategoryForId(Long id)
    {
        return adminCategoryMapper.convertToDTO(adminCategoryService.getCategoryForId(id));
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        CategoryEntity categoryEntity = adminCategoryMapper.convertToEntity(categoryDTO);
        getModelService().save(categoryEntity);
        return adminCategoryMapper.convertToDTO(categoryEntity);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO)
    {
        CategoryEntity categoryEntity = adminCategoryService.getCategoryForId(id);
        adminCategoryMapper.updateCategoryEntityFromDTO(categoryDTO, categoryEntity);
        getModelService().save(categoryEntity);
        return adminCategoryMapper.convertToDTO(categoryEntity);
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

    public AdminCategoryMapper getAdminCategoryMapper()
    {
        return adminCategoryMapper;
    }

    public void setAdminCategoryMapper(AdminCategoryMapper adminCategoryMapper)
    {
        this.adminCategoryMapper = adminCategoryMapper;
    }
}
