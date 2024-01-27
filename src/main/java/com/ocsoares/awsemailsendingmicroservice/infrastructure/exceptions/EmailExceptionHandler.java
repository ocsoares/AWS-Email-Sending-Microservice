package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class EmailExceptionHandler {
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<ExceptionResponse> handleUnsupportedOperationException(
            UnsupportedOperationException exception
    ) {
        ExceptionResponse bodyResponse = new ExceptionResponse(exception.getMessage(),
                HttpStatus.NOT_IMPLEMENTED.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(bodyResponse);
    }

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<ExceptionResponse> handleSendEmailException(SendEmailException exception) {
        var bodyResponse = new ExceptionResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }
}
