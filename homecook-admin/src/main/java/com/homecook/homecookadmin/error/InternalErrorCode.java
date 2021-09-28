package com.homecook.homecookadmin.error;

import java.util.Arrays;

public enum InternalErrorCode
{
    SUCCESS(0),

    INVALID_REQUEST_BODY(10),

    ENTITY_NOT_FOUND(100),


    FILE_PATH_ERROR(200),
    FILE_TYPE_NOT_SUPPORT(201),
    FILE_STORE_FAILED(202),
    IMAGE_FORMAT_NOT_SUPPORT(210),
    IMAGE_RESIZE_ERROR(211),

    PRODUCT_IMAGE_UPLOAD_ERROR(300),
    PRODUCT_IMAGE_ALREADY_IN_USE(301),
    PRODUCT_IMAGE_ADD_MORE_THEN_LIMIT(302),
    PROUDCT_IMAGE_DELETE_ERROR(302),

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
