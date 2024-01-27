package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.InvalidRequestBodyException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RequestBodyControllerHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMessageNotReadableException.class})
    public ExceptionResponse handleInvalidRequestBody(
    ) {
        return new ExceptionResponse("The request body must be in valid JSON format", HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<InvalidRequestBodyException> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ) {
        List<FieldError> fieldErrorsList = exception.getBindingResult().getFieldErrors();

        List<InvalidRequestBodyException> invalidRequestBodyExceptionList = new ArrayList<>();

        fieldErrorsList.forEach(error -> invalidRequestBodyExceptionList.add(
                new InvalidRequestBodyException(error.getField(), error.getDefaultMessage())));

        return invalidRequestBodyExceptionList;
    }
}
