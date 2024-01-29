package com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.javamail;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import com.ocsoares.awsemailsendingmicroservice.main.config.AppEnvironmentVariables;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class JavaMailServiceGateway implements IEmailServiceGateway {
    private final JavaMailSender javaMailSender;
    private final IEmailRepositoryGateway emailRepositoryGateway;
    private final AppEnvironmentVariables appEnvironmentVariables;

    @Override
    public void sendEmail(EmailDomainEntity emailDomainEntity) throws SendEmailException {
        try {
            var simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(this.appEnvironmentVariables.getMailUsername());
            simpleMailMessage.setTo(emailDomainEntity.toRecipient());
            simpleMailMessage.setSubject(emailDomainEntity.subject());
            simpleMailMessage.setText(emailDomainEntity.body());

            this.javaMailSender.send(simpleMailMessage);
            this.emailRepositoryGateway.saveEmail(emailDomainEntity, true);
        } catch (Exception exception) {
            this.emailRepositoryGateway.saveEmail(emailDomainEntity, false);
            throw new SendEmailException();
        }
    }
}
