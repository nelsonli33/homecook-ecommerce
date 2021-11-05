package com.homecook.tappay;


import com.homecook.tappay.exception.TapPayServerConnectException;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApiRequestUtil
{
    public static final long TIME_OUT_SECOND = 30;
    public static final String APPLICATION_JSON = "application/json";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final String CONTENT_TYPE = "content-type";
    public static final String X_API_KEY = "x-api-key";

    public static String postJson(String json, String url, String apiKey) throws TapPayServerConnectException
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT_SECOND, TimeUnit.SECONDS).build();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(X_API_KEY, apiKey).build();
        try (Response response = client.newCall(request).execute())
        {
            return response.body().string();
        }
        catch (IOException e)
        {
            throw new TapPayServerConnectException("TapPay server connect error,please contact TapPay service.");
        }
    }
}
