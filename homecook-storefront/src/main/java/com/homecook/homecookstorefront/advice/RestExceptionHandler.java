package com.homecook.homecookstorefront.advice;

import com.homecook.homecookstorefront.error.InternalErrorCode;
import com.homecook.homecookstorefront.exception.StorefrontServerRuntimeException;
import com.homecook.homecookstorefront.model.Error;
import com.homecook.homecookstorefront.model.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(StorefrontServerRuntimeException.class)
    protected ResponseEntity<ServerResponse> handleServerRuntimeException(StorefrontServerRuntimeException ex, WebRequest request)
    {
        log.error("StorefrontServerRuntimeException {}({})", ex.getErrorCode(), ex.getErrorCode().getCode());
        log.error(ex.getLocalizedMessage());

        log.error("StorefrontServerRuntimeException::stack trace:", ex);
        Throwable cause = ex.getCause();
        log.error("StorefrontServerRuntimeException::cause: " + (cause == null ? "<none>" : cause.getMessage()));
        if (cause != null)
        {
            log.error("cause::stack trace: ", cause);
        }

        List<Error> errors = new ArrayList<>();
        if (ex.getError() != null)
        {
            errors = ex.getError().getAllErrors().stream().map(e -> {
                Error error = new Error();
                error.setField(((FieldError) e).getField());
                error.setMessage(e.getDefaultMessage());
                return error;
            }).collect(Collectors.toList());
        }


        ServerResponse serverResponse = ServerResponse.builder()
                .setCode(ex.getErrorCode().getCode())
                .setMessage(ex.getMessage())
                .setErrors(errors)
                .build();

        return new ResponseEntity<>(serverResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ServerResponse> handleRestOfException(Exception ex, WebRequest request)
    {
        log.error("Unhandled Exception", ex);
        log.error(ex.getLocalizedMessage());

        Throwable cause = ex.getCause();
        log.error("Unhandled exception::cause: " + (cause == null ? "<none>" : cause.getMessage()));
        if (cause != null)
        {
            log.error("cause::stack trace: ", cause);
        }

        InternalErrorCode errorCode = InternalErrorCode.INTERNAL_SERVER_ERROR;
        ServerResponse serverResponse = ServerResponse.builder()
                .setMessage(ex.getMessage())
                .setCode(errorCode.getCode())
                .build();
        return new ResponseEntity<>(serverResponse, HttpStatus.BAD_REQUEST);
    }
}
