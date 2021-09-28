package com.homecook.homecookadmin.config;

import com.google.cloud.storage.Storage;
import com.homecook.homecookcommon.service.impl.DefaultGCSStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:googlecloud.properties"),
        @PropertySource("classpath:project.properties"),
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


}
