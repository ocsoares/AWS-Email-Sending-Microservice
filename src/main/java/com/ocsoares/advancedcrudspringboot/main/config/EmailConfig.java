package com.ocsoares.advancedcrudspringboot.main.config;

import com.ocsoares.advancedcrudspringboot.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.advancedcrudspringboot.application.usecases.mapper.EmailUseCaseMapper;
import com.ocsoares.advancedcrudspringboot.infrastructure.controllers.email.mappers.EmailControllerMapper;
import com.ocsoares.advancedcrudspringboot.infrastructure.gateways.email.jpa.JpaEmailRepositoryGateway;
import com.ocsoares.advancedcrudspringboot.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.advancedcrudspringboot.infrastructure.persistence.repository.jpa.JpaEmailRepository;
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
