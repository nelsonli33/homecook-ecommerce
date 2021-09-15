package com.homecook.homecookadmin.config;

import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookadmin.facade.converter.populator.CategoryPopulator;
import com.homecook.homecookadmin.facade.converter.populator.CategoryReversePopulator;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.impl.PopulatingConverter;
import com.homecook.homecookdomain.model.CategoryModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class HomecookAdminBeanConfig
{

    // <!--- converters and populators --->
    @Bean(name = "adminCategoryReversePopulator")
    public CategoryReversePopulator adminCategoryReversePopulator() {
        return new CategoryReversePopulator();
    }

    @Bean(name = "adminCategoryPopulator")
    public CategoryPopulator adminCategoryPopulator() {
        return new CategoryPopulator();
    }

    @Bean(name = "adminCategoryConverter")
    public Converter<CategoryModel, CategoryData> adminCategoryConverter()
    {
        PopulatingConverter<CategoryModel, CategoryData> converter = new PopulatingConverter<>();
        converter.setTargetClass(CategoryData.class);
        converter.setPopulators(Arrays.asList(adminCategoryPopulator()));
        return converter;
    }
}
