package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.mappers;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos.EmailDTO;
import org.springframework.stereotype.Component;

@Component
public class EmailControllerMapper {
    public EmailDomainEntity toDomain(EmailDTO emailDTO) {
        return new EmailDomainEntity(emailDTO.toRecipient(), emailDTO.subject(), emailDTO.body());
    }
}
