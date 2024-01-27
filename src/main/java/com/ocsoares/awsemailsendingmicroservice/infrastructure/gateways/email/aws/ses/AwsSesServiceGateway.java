package com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.aws.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.main.config.AppEnvironmentVariables;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AwsSesServiceGateway implements IEmailServiceGateway {
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final AppEnvironmentVariables appEnvironmentVariables;

    @Override
    public void sendEmail(EmailDomainEntity emailDomainEntity) throws SendEmailException {
        var sendEmailRequest = new SendEmailRequest().withSource(this.appEnvironmentVariables.getEmailSource())
                .withDestination(new Destination().withToAddresses(emailDomainEntity.toRecipient()))
                .withMessage(new Message().withSubject(new Content(emailDomainEntity.subject()))
                        .withBody(new Body().withText(new Content(emailDomainEntity.body()))));

        try {
            this.amazonSimpleEmailService.sendEmail(sendEmailRequest);
        } catch (AmazonServiceException exception) {
            throw new SendEmailException();
        }
    }
}
