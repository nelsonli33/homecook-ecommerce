package com.homecook.homecookadmin.facade.impl;

import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookadmin.facade.AdminCategoryFacade;
import com.homecook.homecookadmin.service.AdminCategoryService;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookdomain.model.CategoryModel;
import com.homecook.homecookdomain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component(value = "adminCategoryFacade")
public class DefaultAdminCategoryFacade implements AdminCategoryFacade
{

    private AdminCategoryService adminCategoryService;
    private ModelService modelService;
    private Populator<CategoryData, CategoryModel> adminCategoryReversePopulator;
    private Converter<CategoryModel, CategoryData> adminCategoryConverter;

    @Autowired
    public DefaultAdminCategoryFacade(
            @Qualifier(value = "adminCategoryService") AdminCategoryService adminCategoryService,
            @Qualifier(value = "modelService") ModelService modelService,
            @Qualifier(value = "adminCategoryReversePopulator") Populator<CategoryData, CategoryModel> adminCategoryReversePopulator,
            @Qualifier(value = "adminCategoryConverter") Converter<CategoryModel, CategoryData> adminCategoryConverter)
    {
        this.adminCategoryService = adminCategoryService;
        this.modelService = modelService;
        this.adminCategoryReversePopulator = adminCategoryReversePopulator;
        this.adminCategoryConverter = adminCategoryConverter;
    }

    @Override
    public List<CategoryData> getAllCategories()
    {
        List<CategoryModel> allCategories = adminCategoryService.getAllCategories();
        return adminCategoryConverter.convertAll(allCategories);
    }

    @Override
    public CategoryData getCategoryForId(Long id)
    {
        return adminCategoryConverter.convert(adminCategoryService.getCategoryForId(id));
    }

    @Override
    public CategoryData createCategory(CategoryData categoryData)
    {
        CategoryModel categoryModel = new CategoryModel();
        adminCategoryReversePopulator.populate(categoryData, categoryModel);
        adminCategoryService.addParentCategory(categoryModel, categoryData.getParentId());
        return adminCategoryConverter.convert(categoryModel);
    }

    @Override
    public CategoryData updateCategory(Long id, CategoryData categoryData)
    {
        CategoryModel categoryModel = adminCategoryService.getCategoryForId(id);
        adminCategoryReversePopulator.populate(categoryData, categoryModel);
        adminCategoryService.addParentCategory(categoryModel, categoryData.getParentId());
        getModelService().save(categoryModel);
        return adminCategoryConverter.convert(categoryModel);
    }

    @Override
    public void deleteCategory(Long id)
    {
        CategoryModel categoryModel = adminCategoryService.getCategoryForId(id);
        getModelService().remove(categoryModel);
    }


    public void orderingCategories(List<CategoryData> categoryDataList) {
        List<CategoryModel> models = new ArrayList<>();

        for(CategoryData categoryData : categoryDataList) {
            CategoryModel model = adminCategoryService.getCategoryForId(categoryData.getId());
            model.setSortOrder(categoryData.getSortOrder());
            models.add(model);
        }
        getModelService().saveAll(models);
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

    public Populator<CategoryData, CategoryModel> getAdminCategoryReversePopulator()
    {
        return adminCategoryReversePopulator;
    }

    public void setAdminCategoryReversePopulator(Populator<CategoryData, CategoryModel> adminCategoryReversePopulator)
    {
        this.adminCategoryReversePopulator = adminCategoryReversePopulator;
    }

    public Converter<CategoryModel, CategoryData> getAdminCategoryConverter()
    {
        return adminCategoryConverter;
    }

    public void setAdminCategoryConverter(Converter<CategoryModel, CategoryData> adminCategoryConverter)
    {
        this.adminCategoryConverter = adminCategoryConverter;
    }
}
