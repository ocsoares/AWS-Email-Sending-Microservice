package com.ocsoares.awsemailsendingmicroservice.main.config;

import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
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
}
