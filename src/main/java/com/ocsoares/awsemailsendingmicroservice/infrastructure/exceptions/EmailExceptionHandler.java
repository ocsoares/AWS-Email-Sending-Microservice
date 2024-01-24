package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGeneralException() {
        ExceptionResponse bodyResponse = new ExceptionResponse("An unexpected server error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }
}
