package com.ocsoares.advancedcrudspringboot.infrastructure.exceptions;

import com.ocsoares.advancedcrudspringboot.domain.exceptions.response.MessageAndStatusCodeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailExceptionHandler {
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<MessageAndStatusCodeResponse> handleUnsupportedOperationException(
            UnsupportedOperationException exception
    ) {
        MessageAndStatusCodeResponse bodyResponse = new MessageAndStatusCodeResponse(exception.getMessage(),
                HttpStatus.NOT_IMPLEMENTED.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(bodyResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageAndStatusCodeResponse> handleGeneralException() {
        MessageAndStatusCodeResponse bodyResponse = new MessageAndStatusCodeResponse(
                "An unexpected server error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }
}
