package com.homecook.homecookstorefront.error;

import java.util.Arrays;

public enum InternalErrorCode
{
    SUCCESS(0),
    INVALID_REQUEST_BODY(10001),

    ENTITY_NOT_FOUND(20001),

    CUSTOMER_ACCOUNT_EXISTS(30001),
    CUSTOMER_LOGIN_INCORRECT(30002),
    CUSTOMER_NOT_FOUND(30003),

    ADD_TO_CART_INVALID(40001),
    UPDATE_CART_INVALID(40002),

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
