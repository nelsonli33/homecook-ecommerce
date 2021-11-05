package com.homecook.tappay.api;

import com.homecook.tappay.ApiHelper;
import com.homecook.tappay.ApiRequestUtil;
import com.homecook.tappay.Configuration;
import com.homecook.tappay.exception.TapPayServerConnectException;
import com.homecook.tappay.models.PayByPrimeRequest;
import com.homecook.tappay.models.PayByPrimeResponse;

import java.io.IOException;

public class DefaultDirectPayApi implements DirectPayApi
{
    private Configuration config;

    public DefaultDirectPayApi(Configuration config)
    {
        this.config = config;
    }

    @Override
    public PayByPrimeResponse payByPrime(PayByPrimeRequest request) throws IOException, TapPayServerConnectException
    {
        //the base uri for api requests
        String baseUri = config.getBaseUri();

        //prepare query string for API call
        final StringBuilder queryBuilder = new StringBuilder(baseUri
                + "/tpc/payment/pay-by-prime");

        //prepare and invoke the API call request to fetch the response
        String bodyJson = ApiHelper.serialize(request);
        final String responseJson
                = ApiRequestUtil.postJson(bodyJson, queryBuilder.toString(), config.getApiKey());
        return ApiHelper.deserialize(responseJson, PayByPrimeResponse.class);
    }
}
