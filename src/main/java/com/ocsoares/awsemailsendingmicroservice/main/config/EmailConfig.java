package com.ocsoares.awsemailsendingmicroservice.main.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.application.gateways.email.IEmailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.aws.ses.AwsSesServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.jpa.JpaEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa.JpaEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@RequiredArgsConstructor
@Configuration
public class EmailConfig {
    private final AppEnvironmentVariables appEnvironmentVariables;

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
            AmazonSimpleEmailService amazonSimpleEmailService, IEmailRepositoryGateway emailRepositoryGateway,
            AppEnvironmentVariables appEnvironmentVariables
    ) {
        return new AwsSesServiceGateway(amazonSimpleEmailService, emailRepositoryGateway, appEnvironmentVariables);
    }

    @Bean
    public AmazonSNS amazonSNSBuilder() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(
                this.appEnvironmentVariables.getAwsAccessKeyId(), this.appEnvironmentVariables.getAwsSecretKey());

        return AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(this.appEnvironmentVariables.getAwsRegion())
                .build();
    }

    @Bean
    public Topic snsCatalogTopicBuilder() {
        return new Topic().withTopicArn(this.appEnvironmentVariables.getAwsSnsTopicArn());
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(this.appEnvironmentVariables.getAwsRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(this.appEnvironmentVariables.getAwsAccessKeyId(),
                                this.appEnvironmentVariables.getAwsSecretKey()
                        )))
                .build();
    }
}