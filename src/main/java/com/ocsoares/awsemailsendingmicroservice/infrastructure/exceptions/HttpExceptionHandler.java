package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class HttpExceptionHandler {
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ExceptionResponse handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception
    ) {
        return new ExceptionResponse(exception.getMessage(), HttpStatus.METHOD_NOT_ALLOWED.value());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NoResourceFoundException.class)
    public ExceptionResponse handleNoResourceFoundException(
            NoResourceFoundException exception
    ) {
        return new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
