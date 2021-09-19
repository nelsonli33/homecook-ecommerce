package com.homecook.homecookadmin.config;

import com.google.cloud.storage.Storage;
import com.homecook.homecookadmin.dto.ProductImageDTO;
import com.homecook.homecookadmin.facade.converter.ProductImagePopulator;
import com.homecook.homecookcommon.converter.Converter;
import com.homecook.homecookcommon.converter.impl.PopulatingConverter;
import com.homecook.homecookcommon.service.impl.DefaultGCSStorageService;
import com.homecook.homecookentity.entity.ProductImageEntity;
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
    @Bean(name = "adminProductImagePopulator")
    public ProductImagePopulator adminProductImagePopulator() {
        return new ProductImagePopulator();
    }

    @Bean(name = "adminProductImageConverter")
    public Converter<ProductImageEntity, ProductImageDTO> adminProductImageConverter()
    {
        PopulatingConverter<ProductImageEntity, ProductImageDTO> converter = new PopulatingConverter<>();
        converter.setTargetClass(ProductImageDTO.class);
        converter.setPopulators(Arrays.asList(adminProductImagePopulator()));
        return converter;
    }
}
