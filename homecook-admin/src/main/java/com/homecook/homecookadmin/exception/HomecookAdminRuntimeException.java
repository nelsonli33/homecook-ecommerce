package com.homecook.homecookadmin.exception;

import com.homecook.homecookadmin.error.InternalErrorCode;

public class HomecookAdminRuntimeException extends RuntimeException
{
    private final InternalErrorCode errorCode;

    public HomecookAdminRuntimeException(InternalErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message, Throwable cause)
    {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public InternalErrorCode getErrorCode()
    {
        return errorCode;
    }
}
