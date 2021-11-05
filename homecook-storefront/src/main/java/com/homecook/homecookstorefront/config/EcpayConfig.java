package com.homecook.homecookstorefront.config;

import ecpay.invoice.integration.EcpayInvoiceClient;
import ecpay.logistics.integration.EcpayLogisticsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EcpayConfig
{
    @Bean(name = "ecpayLogisticsClient")
    public EcpayLogisticsClient ecpayLogisticsClient()
    {
        return new EcpayLogisticsClient.Builder()
                .operatingMode(ecpay.logistics.integration.OperatingMode.TEST)
                .MerchantID("2000933")
                .HashKey("XBERn1YOvpM9nfZc")
                .HashIV("h1ONHk4P4yqbl5LK")
                .build();
    }

    @Bean(name = "ecpayInvoiceClient")
    public EcpayInvoiceClient ecpayInvoiceClient()
    {
        return new EcpayInvoiceClient.Builder()
                .operatingMode(ecpay.invoice.integration.OperatingMode.TEST)
                .MerchantID("2000132")
                .HashKey("ejCk326UnaZWKisg")
                .HashIV("q9jcZX8Ib9LM8wYk")
                .build();
    }
}
