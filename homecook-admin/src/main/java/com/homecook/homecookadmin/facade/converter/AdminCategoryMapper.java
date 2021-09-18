package com.homecook.homecookadmin.facade.converter;

import com.homecook.homecookadmin.dto.CategoryDTO;
import com.homecook.homecookcommon.converter.Mapper;
import com.homecook.homecookentity.entity.CategoryEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component(value = "adminCategoryMapper")
public class AdminCategoryMapper implements Mapper<CategoryEntity, CategoryDTO>
{
    @Override
    public CategoryDTO convertToDto(CategoryEntity model)
    {
        Assert.notNull(model, "Parameter categoryModel cannot be null.");
        CategoryDTO data = new CategoryDTO();
        populateModelToDto(model, data);
        return data;
    }

    @Override
    public void populateModelToDto(CategoryEntity model, CategoryDTO data)
    {
        data.setId(model.getId());
        data.setName(model.getName());
        data.setSortOrder(model.getSortOrder());
        data.setParentId(model.getParentId());
        data.setMetaTitle(model.getMetaTitle());
        data.setMetaDescription(model.getMetaDescription());
    }

    @Override
    public CategoryEntity convertToModel(CategoryDTO data)
    {
        CategoryEntity model = new CategoryEntity();
        populateDtoToModel(data, model);
        return model;
    }

    @Override
    public void populateDtoToModel(CategoryDTO data, CategoryEntity model)
    {
        model.setName(data.getName());
        model.setSortOrder(data.getSortOrder());
        model.setMetaTitle(data.getMetaTitle());
        model.setMetaDescription(data.getMetaDescription());
    }


}
