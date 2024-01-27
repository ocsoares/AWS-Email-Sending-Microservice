package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class EmailExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ResponseBody
    @ExceptionHandler(UnsupportedOperationException.class)
    public ExceptionResponse handleUnsupportedOperationException(
            UnsupportedOperationException exception
    ) {
        return new ExceptionResponse(exception.getMessage(), HttpStatus.NOT_IMPLEMENTED.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(SendEmailException.class)
    public ExceptionResponse handleSendEmailException(SendEmailException exception) {
        return new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
