package com.ocsoares.awsemailsendingmicroservice.infrastructure.exceptions;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Como os outros Handlers foram anotados com "@Order(Ordered.HIGHEST_PRECEDENCE)", e esse aqui NÃO foi, o Padrão
// dos Order é "LOWEST_PRECEDENCE", então ESSE Handler vai ser Executado por ÚLTIMO!!!
@RestControllerAdvice
public class GenericExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleGeneralException(Exception exception) {
        return new ExceptionResponse("An unexpected server error occurred", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
