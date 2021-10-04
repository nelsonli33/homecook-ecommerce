package com.homecook.homecookstorefront.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:googlecloud.properties"),
})
public class StorefrontBeanConfig
{
}