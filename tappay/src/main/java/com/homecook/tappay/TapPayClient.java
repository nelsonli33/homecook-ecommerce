package com.homecook.tappay;

import com.homecook.tappay.api.DefaultDirectPayApi;
import com.homecook.tappay.api.DefaultRecordApi;
import com.homecook.tappay.api.DirectPayApi;
import com.homecook.tappay.api.RecordApi;

public class TapPayClient implements TapPay
{
    private DirectPayApi directPayApi;
    private RecordApi recordApi;
    /**
     * TapPay partner key
     */
    private final String apiKey;

    /**
     * Current API environment.
     */
    private final Environment environment;


    public TapPayClient(String apiKey, Environment environment)
    {
        this.apiKey = apiKey;
        this.environment = environment;

        directPayApi = new DefaultDirectPayApi(this);
        recordApi = new DefaultRecordApi(this);
    }

    @Override
    public DirectPayApi getDirectPayApi()
    {
        return directPayApi;
    }

    @Override
    public RecordApi getRecordApi()
    {
        return recordApi;
    }

    @Override
    public String getApiKey()
    {
        return apiKey;
    }

    @Override
    public Environment getEnvironment()
    {
        return environment;
    }

    @Override
    public String getBaseUri()
    {
        if (environment.equals(Environment.PRODUCTION))
        {
            return "https://prod.tappaysdk.com";
        }

        if (environment.equals(Environment.SANDBOX))
        {
            return "https://sandbox.tappaysdk.com";
        }

        return "https://prod.tappaysdk.com";
    }


    public static final class Builder
    {
        private String apiKey;
        private Environment environment = Environment.PRODUCTION;


        public Builder apiKey(String apiKey)
        {
            if (apiKey == null)
            {
                throw new NullPointerException("ApiKey(Partner key) cannot be null.");
            }
            this.apiKey = apiKey;
            return this;
        }

        public Builder environment(Environment environment)
        {
            this.environment = environment;
            return this;
        }

        public TapPayClient build()
        {
            return new TapPayClient(apiKey, environment);
        }
    }
}
