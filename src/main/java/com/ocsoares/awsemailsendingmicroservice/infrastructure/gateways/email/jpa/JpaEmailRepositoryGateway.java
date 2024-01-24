package com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.jpa;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa.JpaEmailRepository;

public class JpaEmailRepositoryGateway implements IEmailRepositoryGateway {
    private final JpaEmailRepository jpaEmailRepository;
    private final EmailPersistenceEntityMapper emailPersistenceEntityMapper;

    public JpaEmailRepositoryGateway(
            JpaEmailRepository jpaEmailRepository, EmailPersistenceEntityMapper emailPersistenceEntityMapper
    ) {
        this.jpaEmailRepository = jpaEmailRepository;
        this.emailPersistenceEntityMapper = emailPersistenceEntityMapper;
    }

    @Override
    public EmailDomainEntity saveEmail(EmailDomainEntity emailDomainEntity) {
        return null;
    }
}