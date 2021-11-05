package com.homecook.tappay;

import com.homecook.tappay.api.DirectPayApi;
import com.homecook.tappay.api.RecordApi;
import com.homecook.tappay.exception.ArgumentsValidException;
import com.homecook.tappay.exception.TapPayServerConnectException;
import com.homecook.tappay.models.*;

import java.io.IOException;

public class Example
{
    public static void main(String[] args)
    {
//        payByPrime();
        TapPay tappay = new TapPayClient.Builder()
                .environment(Environment.SANDBOX)
                .apiKey("partner_vKX6NAxfV6mpdcSx6BlHJGy4TyaJmAqW3PnTZbl7wSzOCQhoWDDXa0FK")
                .build();

        try
        {
            RecordRequest request = new RecordRequest.Builder()
                    .partnerKey(tappay.getApiKey())
                    .filters(new RecordFilters.Builder().recTradeId("D20211103S5W5Vx").build())
                    .build();
            final RecordApi recordApi = tappay.getRecordApi();

            final RecordResponse record = recordApi.getRecord(request);
            System.out.println(record);
        }
        catch (ArgumentsValidException e)
        {
            e.printStackTrace();
        }
        catch (TapPayServerConnectException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void payByPrime()
    {
        PayByPrimeRequest request = new PayByPrimeRequest.Builder()
                .prime("test_3a2fb2b7e892b914a03c95dd4dd5dc7970c908df67a49527c0a648b2bc9")
                .partnerKey("partner_vKX6NAxfV6mpdcSx6BlHJGy4TyaJmAqW3PnTZbl7wSzOCQhoWDDXa0FK")
                .merchantId("toujia248_CTBC")
                .details("TapPay Test")
                .amount(1000)
                .cardholder(new Cardholder.Builder()
                        .phoneNumber("+886923456789")
                        .name("王小明")
                        .email("LittleMing@Wang.com")
                        .zipCode("100")
                        .address("台北市天龍區芝麻街1號1樓")
                        .nationalId("A123456789")
                        .build()
                ).build();

        TapPay tappay = new TapPayClient.Builder()
                .environment(Environment.SANDBOX)
                .apiKey("partner_vKX6NAxfV6mpdcSx6BlHJGy4TyaJmAqW3PnTZbl7wSzOCQhoWDDXa0FK")
                .build();

        final DirectPayApi directPayApi = tappay.getDirectPayApi();
        final PayByPrimeResponse payByPrimeResponse;
        try
        {
            payByPrimeResponse = directPayApi.payByPrime(request);
            System.out.println(payByPrimeResponse);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TapPayServerConnectException e)
        {
            e.printStackTrace();
        }
    }
}


