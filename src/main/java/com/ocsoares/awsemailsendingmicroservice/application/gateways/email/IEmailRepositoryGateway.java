package com.ocsoares.awsemailsendingmicroservice.application.gateways.email;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;

public interface IEmailRepositoryGateway {
    void saveEmail(EmailDomainEntity emailDomainEntity, Boolean sent);
}
