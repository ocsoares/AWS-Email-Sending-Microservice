package com.ocsoares.awsemailsendingmicroservice.application.usecases.mapper;

import com.ocsoares.awsemailsendingmicroservice.application.usecases.response.EmailResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;

import java.util.List;

// Mudei os Parâmetros do User para o Email só para PARAR de dar Erro
// OBS: Mas ainda NÃO SEI o EmailResponse q vou por...
public class EmailUseCaseMapper {
    public EmailResponse toResponse(EmailDomainEntity emailDomainEntity) {
        return new EmailResponse(emailDomainEntity.to());
    }

    public List<EmailResponse> toResponseList(List<EmailDomainEntity> emailDomainEntityList) {
        return emailDomainEntityList.stream().map(this::toResponse).toList();
    }
}