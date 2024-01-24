package com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response;

public record InvalidRequestBodyException(String field, String message) {
}
