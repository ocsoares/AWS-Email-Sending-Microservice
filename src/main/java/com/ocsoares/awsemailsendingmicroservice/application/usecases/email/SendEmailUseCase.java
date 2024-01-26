package com.ocsoares.awsemailsendingmicroservice.application.usecases.email;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.interfaces.IUseCaseWithArgument;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.mapper.EmailUseCaseMapper;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.response.EmailResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// Usando o "RequiredArgsConstructor" faz INJETAR (Autowired) pelo CONSTRUCTOR da Classe, se estiver usando um "Stereotype"
// como o "Service", por exemplo!!!
@RequiredArgsConstructor
@Service
public class SendEmailUseCase implements IUseCaseWithArgument<EmailResponse, EmailDomainEntity, Exception> {
    private final IEmailRepositoryGateway emailRepositoryGateway;
    private final EmailUseCaseMapper emailUseCaseMapper;

    @Override
    public EmailResponse execute(EmailDomainEntity emailDomainEntity) throws Exception {
        return this.emailUseCaseMapper.toResponse(emailDomainEntity);
    }
}
