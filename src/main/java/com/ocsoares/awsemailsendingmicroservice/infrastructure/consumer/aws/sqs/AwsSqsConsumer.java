package com.ocsoares.awsemailsendingmicroservice.infrastructure.consumer.aws.sqs;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.email.SendEmailException;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class AwsSqsConsumer {
    private final IEmailServiceGateway emailServiceGateway;

    private static String extractAttribute(String message, String attribute) {
        String pattern = attribute + "=([^,\\]]+)";
        Matcher matcher = Pattern.compile(pattern).matcher(message);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    @SqsListener("email-sending-microservice")
    public void listener(AwsSqsListenerResponse awsSqsListenerResponse) throws SendEmailException {
        String message = awsSqsListenerResponse.getMessage();

        String toRecipient = extractAttribute(message, "toRecipient");
        String subject = extractAttribute(message, "subject");
        String body = extractAttribute(message, "body");

        var emailDomainEntity = new EmailDomainEntity(toRecipient, subject, body);

        this.emailServiceGateway.sendEmail(emailDomainEntity);
    }
}
