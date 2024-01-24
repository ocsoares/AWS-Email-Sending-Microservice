package com.ocsoares.awsemailsendingmicroservice.application.usecases.email;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.interfaces.IUseCaseWithArgument;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.mapper.EmailUseCaseMapper;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.response.EmailResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;

public class SendEmailUseCase implements IUseCaseWithArgument<EmailResponse, EmailDomainEntity, Exception> {
    private IEmailRepositoryGateway emailRepositoryGateway;
    private EmailUseCaseMapper emailUseCaseMapper;

    @Override
    public EmailResponse execute(EmailDomainEntity parameter) throws Exception {
        return null;
    }
}
