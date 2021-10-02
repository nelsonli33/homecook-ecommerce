package com.homecook.homecookstorefront.exception;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import org.springframework.validation.BindingResult;

public class StorefrontServerRuntimeException extends RuntimeException
{

    private InternalErrorCode errorCode;
    private BindingResult error;


    public StorefrontServerRuntimeException(InternalErrorCode errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public StorefrontServerRuntimeException(InternalErrorCode errorCode, String message, BindingResult error)
    {
        super(message);
        this.errorCode = errorCode;
        this.error = error;
    }

    public StorefrontServerRuntimeException(InternalErrorCode errorCode, String message, Throwable cause)
    {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public StorefrontServerRuntimeException(InternalErrorCode errorCode, Throwable cause)
    {
        super(cause);
        this.errorCode = errorCode;
    }

    public static StorefrontServerRuntimeException makeInternalServerError(Throwable cause)
    {
        return new StorefrontServerRuntimeException(InternalErrorCode.INTERNAL_SERVER_ERROR, cause);
    }

    public InternalErrorCode getErrorCode()
    {
        return errorCode;
    }

    public BindingResult getError()
    {
        return error;
    }
}
