package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable
{
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static CommonResponse error(Integer errorCode, String errorMessage) {
        return new CommonResponse(errorCode, errorMessage);
    }

    public CommonResponse()
    {
    }

    public CommonResponse(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }
}
