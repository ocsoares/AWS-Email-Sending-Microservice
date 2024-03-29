package com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.jpa;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.entity.EmailPersistenceEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa.JpaEmailRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JpaEmailRepositoryGateway implements IEmailRepositoryGateway {
    private final JpaEmailRepository jpaEmailRepository;
    private final EmailPersistenceEntityMapper emailPersistenceEntityMapper;

    @Override
    public void saveEmail(EmailDomainEntity emailDomainEntity, Boolean sent) {
        EmailPersistenceEntity emailPersistenceEntity = this.emailPersistenceEntityMapper.toPersistence(
                emailDomainEntity, sent);

        this.jpaEmailRepository.save(emailPersistenceEntity);
    }
}