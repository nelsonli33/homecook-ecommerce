package com.homecook.homecookadmin.facade.converter.populator;

import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookcommon.converter.Populator;
import com.homecook.homecookdomain.model.CategoryModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class CategoryPopulator implements Populator<CategoryModel, CategoryData>
{
    @Override
    public void populate(CategoryModel source, CategoryData target)
    {
        Assert.notNull(source, "Parameter categoryModel cannot be null.");
        Assert.notNull(target, "Parameter CategoryData cannot be null.");

        target.setId(source.getId());
        target.setName(StringUtils.defaultString(source.getName()));
        target.setMetaTitle(StringUtils.defaultString(source.getMetaTitle()));
        target.setMetaDescription(StringUtils.defaultString(source.getMetaDescription()));
        target.setSortOrder(source.getSortOrder());
        target.setParentId(source.getParentId());
    }
}
