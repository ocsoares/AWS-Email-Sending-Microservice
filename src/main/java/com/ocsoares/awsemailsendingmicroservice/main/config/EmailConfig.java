package com.ocsoares.awsemailsendingmicroservice.main.config;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.mapper.EmailUseCaseMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.mappers.EmailControllerMapper;
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
    public EmailPersistenceEntityMapper emailPersistenceEntityMapper() {
        return new EmailPersistenceEntityMapper();
    }

    @Bean
    public EmailUseCaseMapper emailUseCaseMapper() {
        return new EmailUseCaseMapper();
    }

    @Bean
    public EmailControllerMapper emailControllerMapper() {
        return new EmailControllerMapper();
    }
}
