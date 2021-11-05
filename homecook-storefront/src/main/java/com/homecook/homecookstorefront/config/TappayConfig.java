package com.homecook.homecookstorefront.config;

import com.homecook.tappay.Environment;
import com.homecook.tappay.TapPayClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TappayConfig
{
    @Value("${tappay.environment}")
    private String tapPayEnv;

    @Value("${tappay.partner.key}")
    private String tapPayPartnerKey;

    @Bean(name = "tapPayClient")
    public TapPayClient tapPayClient()
    {
        return new TapPayClient.Builder()
                .environment(Environment.fromString(tapPayEnv))
                .apiKey(tapPayPartnerKey)
                .build();
    }


}
