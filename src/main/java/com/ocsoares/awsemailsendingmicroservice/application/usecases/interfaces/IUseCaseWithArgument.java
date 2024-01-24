package com.ocsoares.awsemailsendingmicroservice.application.usecases.interfaces;

public interface IUseCaseWithArgument<R, P, E extends Exception> {
    R execute(P parameter) throws E;
}
