package com.homecook.tappay.api;

import com.homecook.tappay.exception.TapPayServerConnectException;
import com.homecook.tappay.models.PayByPrimeRequest;
import com.homecook.tappay.models.PayByPrimeResponse;

import java.io.IOException;

public interface DirectPayApi
{
    PayByPrimeResponse payByPrime(PayByPrimeRequest request) throws IOException, TapPayServerConnectException;
}
