package com.ocsoares.awsemailsendingmicroservice.application.gateways.email;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;

public interface IEmailRepositoryGateway {
    EmailDomainEntity saveEmail(EmailDomainEntity emailDomainEntity);
}
