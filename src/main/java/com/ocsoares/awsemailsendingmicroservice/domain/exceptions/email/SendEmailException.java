package com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email;

public class SendEmailException extends Exception {
    public static final String EXCEPTION_MESSAGE = "An error occurred while trying to send the email. Try again later";

    public SendEmailException() {
        super(EXCEPTION_MESSAGE);
    }
}
