package com.ocsoares.advancedcrudspringboot.infrastructure.gateways.user.jpa;

import com.ocsoares.advancedcrudspringboot.application.gateways.user.IEmailRepositoryGateway;
import com.ocsoares.advancedcrudspringboot.domain.entity.EmailDomainEntity;
import com.ocsoares.advancedcrudspringboot.infrastructure.mappers.UserPersistenceEntityMapper;
import com.ocsoares.advancedcrudspringboot.infrastructure.persistence.repository.jpa.JpaUserRepository;

public class JpaEmailRepositoryGateway implements IEmailRepositoryGateway {
    private final JpaUserRepository jpaUserRepository;
    private final UserPersistenceEntityMapper userPersistenceEntityMapper;

    public JpaEmailRepositoryGateway(
            JpaUserRepository jpaUserRepository, UserPersistenceEntityMapper userPersistenceEntityMapper
    ) {
        this.jpaUserRepository = jpaUserRepository;
        this.userPersistenceEntityMapper = userPersistenceEntityMapper;
    }

    @Override
    public EmailDomainEntity saveEmail(EmailDomainEntity emailDomainEntity) {
        return null;
    }
}