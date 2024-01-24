package com.ocsoares.advancedcrudspringboot.application.gateways.email;

import com.ocsoares.advancedcrudspringboot.domain.entity.EmailDomainEntity;

public interface IEmailRepositoryGateway {
    EmailDomainEntity saveEmail(EmailDomainEntity emailDomainEntity);
}
