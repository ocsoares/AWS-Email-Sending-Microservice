package com.ocsoares.awsemailsendingmicroservice.main.config;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.aws.ses.AwsSesServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.jpa.JpaEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa.JpaEmailRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Bean
    public IEmailRepositoryGateway emailRepositoryGateway(
            JpaEmailRepository jpaEmailRepository, EmailPersistenceEntityMapper emailPersistenceEntityMapper
    ) {
        return new JpaEmailRepositoryGateway(jpaEmailRepository, emailPersistenceEntityMapper);
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard().build();
    }

    @Bean
    public IEmailServiceGateway emailServiceGateway(
            AmazonSimpleEmailService amazonSimpleEmailService, AppEnvironmentVariables appEnvironmentVariables
    ) {
        return new AwsSesServiceGateway(amazonSimpleEmailService, appEnvironmentVariables);
    }
}
