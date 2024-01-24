package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException exception
    ) {
        ExceptionResponse bodyResponse = new ExceptionResponse(exception.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED.value()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(bodyResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoResourceFoundException(
            NoResourceFoundException exception
    ) {
        ExceptionResponse bodyResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyResponse);
    }
}
