package com.homecook.homecookadmin.error;

import java.util.Arrays;

public enum InternalErrorCode
{
    SUCCESS(0),

    INVALID_REQUEST_BODY(10),

    MODELID_NOT_FOUND(100),

    INTERNAL_SERVER_ERROR(999);
    ;

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
