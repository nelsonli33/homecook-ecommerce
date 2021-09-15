package com.homecook.homecookadmin.facade.converter.populator;

import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookdomain.model.CategoryModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

public class CategoryReversePopulator implements Populator<CategoryData, CategoryModel>
{
    @Override
    public void populate(CategoryData source, CategoryModel target)
    {
        Assert.notNull(source, "Parameter categoryData cannot be null.");
        Assert.notNull(target, "Parameter categoryModel cannot be null.");

        target.setName(source.getName());
        target.setSortOrder(source.getSortOrder());
        target.setMetaTitle(source.getMetaTitle());
        target.setMetaDescription(source.getMetaDescription());
    }
}
