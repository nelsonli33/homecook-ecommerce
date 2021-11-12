package com.homecook.homecookstorefront.error;

import java.util.Arrays;

public enum InternalErrorCode
{
    SUCCESS(0),
    INVALID_REQUEST_BODY(10001),

    ENTITY_NOT_FOUND(20001),

    CUSTOMER_ACCOUNT_EXISTS(30001),
    CUSTOMER_LOGIN_INCORRECT(30002),
    CUSTOMER_BIRTHDAY_FORMAT_INVALID(30003),
    CUSTOMER_NOT_FOUND(30004),
    ADD_TO_CART_INVALID(40001),
    UPDATE_CART_INVALID(40002),
    CART_CHECKOUT_ERROR(40003),

    INVOICE_LOVE_CODE_NOT_EXIST(45000),


    ECPAY_INVOICE_CHECK_LOVE_CODE_ERROR(50001),


    CHECKOUT_APPLIED_SHIPPING_ADDRESS_ERROR(60001),
    CHECKOUT_PAYMENT_METHOD_ERROR(60002),
    CHECKOUT_MISSING_SHIPPING_ADDRESS(60003),
    CHECKOUT_MISSING_SHIPPING_METHODS(60004),
    CHECKOUT_MISSING_PAYMENT_METHODS(60005),

    ORDER_CODE_NOT_FOUND(70001),
    ORDER_ALREADY_PAID(70002),

    INSUFFICIENT_STOCK_ERROR(80001),

    PAYMENT_GATEWAY_CONNECTION_ERROR(90001),
    PAYMENT_AUTHORIZATION_ORDER_NUMBER_MISMATCH(90003),
    PSP_API_ERROR(90004),

    INTERNAL_SERVER_ERROR(999);

    private final int code;

    InternalErrorCode(int code)
    {
        this.code = code;
    }

    public static InternalErrorCode fromCode(int code)
    {
        return Arrays
                .stream(InternalErrorCode.values())
                .filter(e -> e.getCode() == code)
                .findFirst()
                .get();
    }

    public int getCode()
    {
        return code;
    }
}
