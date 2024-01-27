package com.ocsoares.awsemailsendingmicroservice.application.usecases.mapper;

import com.ocsoares.awsemailsendingmicroservice.application.usecases.response.EmailResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailUseCaseMapper {
    public EmailResponse toResponse(EmailDomainEntity emailDomainEntity) {
        return new EmailResponse(emailDomainEntity.toRecipient());
    }

    public List<EmailResponse> toResponseList(List<EmailDomainEntity> emailDomainEntityList) {
        return emailDomainEntityList.stream().map(this::toResponse).toList();
    }
}