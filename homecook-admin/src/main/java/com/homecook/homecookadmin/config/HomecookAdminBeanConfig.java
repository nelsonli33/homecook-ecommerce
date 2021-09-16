package com.homecook.homecookadmin.config;

import com.google.cloud.storage.Storage;
import com.homecook.homecookadmin.dto.CategoryData;
import com.homecook.homecookadmin.dto.ProductImageData;
import com.homecook.homecookadmin.facade.converter.populator.CategoryPopulator;
import com.homecook.homecookadmin.facade.converter.populator.CategoryReversePopulator;
import com.homecook.homecookadmin.facade.converter.populator.ProductImagePopulator;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.impl.PopulatingConverter;
import com.homecook.homecookcommon.service.GCSStorageService;
import com.homecook.homecookcommon.service.impl.DefaultGCSStorageService;
import com.homecook.homecookdomain.model.CategoryModel;
import com.homecook.homecookdomain.model.ProductImageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.util.Arrays;

@Configuration
@PropertySources({
        @PropertySource("classpath:googlecloud.properties"),
})
public class HomecookAdminBeanConfig
{

    @Autowired
    private Storage storage;

    @Value( "${cloud.google.storage.bucket}" )
    private String bucketName;

    // <!-- services -->
    @Bean(name = "gcsStorageService")
    public DefaultGCSStorageService gcsStorageService() {
        return new DefaultGCSStorageService(bucketName, storage);
    }

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

    @Bean(name = "adminProductImagePopulator")
    public ProductImagePopulator adminProductImagePopulator() {
        return new ProductImagePopulator();
    }

    @Bean(name = "adminProductImageConverter")
    public Converter<ProductImageModel, ProductImageData> adminProductImageConverter()
    {
        PopulatingConverter<ProductImageModel, ProductImageData> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductImageData.class);
        converter.setPopulators(Arrays.asList(adminProductImagePopulator()));
        return converter;
    }
}
