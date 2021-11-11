package com.homecook.homecookadmin.exception;

import com.homecook.homecookadmin.error.InternalErrorCode;
import org.springframework.validation.BindingResult;

public class HomecookAdminRuntimeException extends RuntimeException
{

    private InternalErrorCode errorCode;
    private BindingResult error;
    private Object data;


    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message, Object data)
    {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message, BindingResult error)
    {
        super(message);
        this.errorCode = errorCode;
        this.error = error;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, String message, Throwable cause)
    {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public HomecookAdminRuntimeException(InternalErrorCode errorCode, Throwable cause)
    {
        super(cause);
        this.errorCode = errorCode;
    }

    public static HomecookAdminRuntimeException makeInternalServerError(Throwable cause)
    {
        return new HomecookAdminRuntimeException(InternalErrorCode.INTERNAL_SERVER_ERROR, cause);
    }

    public InternalErrorCode getErrorCode()
    {
        return errorCode;
    }

    public Object getData()
    {
        return data;
    }

    public BindingResult getError()
    {
        return error;
    }
}
