package com.homecook.homecookadmin.advice;

import com.homecook.homecookadmin.model.CommonResponse;
import com.homecook.homecookadmin.exception.HomecookAdminRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandlerAdvice.class);

    @ExceptionHandler(HomecookAdminRuntimeException.class)
    protected ResponseEntity<CommonResponse> handleServerRuntimeException(HomecookAdminRuntimeException ex) {

        log.error("HomecookAdminRuntimeException {}({})", ex.getErrorCode(), ex.getErrorCode().getCode());
        log.error(ex.getLocalizedMessage());

        log.error("HomecookAdminRuntimeException::stack trace:", ex);
        Throwable cause = ex.getCause();
        log.error("HomecookAdminRuntimeException::cause: " + (cause == null ? "<none>" : cause.getMessage()));
        if (cause != null) {
            log.error("cause::stack trace: ", cause);
        }

        CommonResponse response = CommonResponse.error(ex.getErrorCode().getCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
