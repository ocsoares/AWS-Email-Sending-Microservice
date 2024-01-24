package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.interfaces;

public interface IControllerWithoutArgument<R> {
    R handle();
}
