package com.ocsoares.awsemailsendingmicroservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.main.config.AppEnvironmentVariables;
import org.springframework.mail.SimpleMailMessage;

public class TestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final AppEnvironmentVariables appEnvironmentVariables = new AppEnvironmentVariables();

    public static EmailDomainEntity createEmailBody() {
        return new EmailDomainEntity("johndoe@gmail.com", "An email to John Doe", "Hello John Doe !");
    }

    public static SimpleMailMessage createSimpleMailMessage() {
        var emailDomainEntity = TestUtils.createEmailBody();

        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(TestUtils.appEnvironmentVariables.getMailUsername());
        simpleMailMessage.setTo(emailDomainEntity.toRecipient());
        simpleMailMessage.setSubject(emailDomainEntity.subject());
        simpleMailMessage.setText(emailDomainEntity.body());

        return simpleMailMessage;
    }
}
