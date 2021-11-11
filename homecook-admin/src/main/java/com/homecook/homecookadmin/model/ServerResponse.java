package com.homecook.homecookadmin.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

public class ServerResponse<T> implements Serializable
{
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Error> errors;
    private T data;

    public static ServerResponse error(Integer errorCode, String errorMessage)
    {
        return new ServerResponse(errorCode, errorMessage);
    }

    public static ServerResponse success()
    {
        return new ServerResponse(0, "success");
    }

    public ServerResponse()
    {
    }

    public ServerResponse(Integer code, String message)
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

    public List<Error> getErrors()
    {
        return errors;
    }

    public void setErrors(List<Error> errors)
    {
        this.errors = errors;
    }

    public static Builder builder()
    {
        return new Builder();
    }


    public static final class Builder<T>
    {
        private Integer code;
        private String message;
        private List<Error> errors;
        private T data;

        private Builder()
        {
        }

        public Builder setCode(Integer code)
        {
            this.code = code;
            return this;
        }

        public Builder setMessage(String message)
        {
            this.message = message;
            return this;
        }

        public Builder setErrors(List<Error> errors)
        {
            this.errors = errors;
            return this;
        }

        public Builder setData(T data)
        {
            this.data = data;
            return this;
        }

        public ServerResponse build()
        {
            ServerResponse serverResponse = new ServerResponse();
            serverResponse.setCode(code);
            serverResponse.setMessage(message);
            serverResponse.setErrors(errors);
            serverResponse.setData(data);
            return serverResponse;
        }
    }
}
