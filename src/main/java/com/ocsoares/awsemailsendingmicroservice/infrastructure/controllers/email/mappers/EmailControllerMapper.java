package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.mappers;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos.EmailDTO;

public class EmailControllerMapper {
    public EmailDomainEntity toDomain(EmailDTO emailDTO) {
        return new EmailDomainEntity(emailDTO.name(), emailDTO.email(), emailDTO.password());
    }
}
