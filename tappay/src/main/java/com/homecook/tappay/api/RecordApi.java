package com.homecook.tappay.api;

import com.homecook.tappay.exception.TapPayServerConnectException;
import com.homecook.tappay.models.RecordRequest;
import com.homecook.tappay.models.RecordResponse;

import java.io.IOException;

public interface RecordApi
{
    RecordResponse getRecord(RecordRequest request) throws IOException, TapPayServerConnectException;
}
