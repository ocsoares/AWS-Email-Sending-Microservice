package com.ocsoares.advancedcrudspringboot.application.usecases.email;

import com.ocsoares.advancedcrudspringboot.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.advancedcrudspringboot.application.usecases.interfaces.IUseCaseWithArgument;
import com.ocsoares.advancedcrudspringboot.application.usecases.mapper.EmailUseCaseMapper;
import com.ocsoares.advancedcrudspringboot.application.usecases.response.EmailResponse;
import com.ocsoares.advancedcrudspringboot.domain.entity.EmailDomainEntity;

public class SendEmailUseCase implements IUseCaseWithArgument<EmailResponse, EmailDomainEntity, Exception> {
    private IEmailRepositoryGateway emailRepositoryGateway;
    private EmailUseCaseMapper emailUseCaseMapper;

    @Override
    public EmailResponse execute(EmailDomainEntity parameter) throws Exception {
        return null;
    }
}
