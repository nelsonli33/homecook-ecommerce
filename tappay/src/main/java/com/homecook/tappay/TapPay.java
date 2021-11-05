package com.homecook.tappay;

import com.homecook.tappay.api.DirectPayApi;
import com.homecook.tappay.api.RecordApi;

public interface TapPay extends Configuration
{
    DirectPayApi getDirectPayApi();

    RecordApi getRecordApi();
}
