package com.ocsoares.awsemailsendingmicroservice.application.gateways.email;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;

public interface IEmailServiceGateway {
    void sendEmail(EmailDomainEntity emailDomainEntity) throws SendEmailException;
}
