package com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers;

import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.entity.EmailPersistenceEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailPersistenceEntityMapper {
    public EmailPersistenceEntity toPersistence(EmailDomainEntity emailDomainEntity) {
        return new EmailPersistenceEntity(emailDomainEntity.toRecipient(), emailDomainEntity.subject(),
                emailDomainEntity.body()
        );
    }

    public EmailDomainEntity toDomain(EmailPersistenceEntity emailPersistenceEntity) {
        return new EmailDomainEntity(emailPersistenceEntity.getToRecipient(), emailPersistenceEntity.getSubject(),
                emailPersistenceEntity.getBody()
        );
    }

    public List<EmailDomainEntity> toDomainList(List<EmailPersistenceEntity> emailPersistenceEntityList) {
        return emailPersistenceEntityList.stream().map(this::toDomain).toList();
    }
}
