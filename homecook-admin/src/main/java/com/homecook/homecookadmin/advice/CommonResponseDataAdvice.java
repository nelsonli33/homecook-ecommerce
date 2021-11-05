package com.homecook.homecookadmin.advice;

import com.homecook.homecookadmin.annotation.IgnoreResponseAdvice;
import com.homecook.homecookadmin.model.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object>
{
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass)
    {
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class) ||
                methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)
        )
        {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse)
    {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) serverHttpResponse).getServletResponse();

        if (servletResponse.getStatus() == 200)
        {
            CommonResponse<Object> response = new CommonResponse<>(0, "success");

            if (null == o)
            {
                return response;
            }
            else if (o instanceof CommonResponse)
            {
                response = (CommonResponse<Object>) o;
            }
            else
            {
                response.setData(o);
            }

            return response;
        }

        return o;
    }
}
