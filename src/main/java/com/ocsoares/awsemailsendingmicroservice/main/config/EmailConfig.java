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
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.javamail.JavaMailServiceGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.gateways.email.jpa.JpaEmailRepositoryGateway;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.mappers.EmailPersistenceEntityMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa.JpaEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.Properties;

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

    // AwsSesServiceGateway !!
//    @Bean
//    public IEmailServiceGateway emailServiceGateway(
//            AmazonSimpleEmailService amazonSimpleEmailService, IEmailRepositoryGateway emailRepositoryGateway,
//            AppEnvironmentVariables appEnvironmentVariables
//    ) {
//        return new AwsSesServiceGateway(amazonSimpleEmailService, emailRepositoryGateway, appEnvironmentVariables);
//    }

    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.appEnvironmentVariables.getMailHost());
        mailSender.setPort(Integer.parseInt(this.appEnvironmentVariables.getMailPort()));
        mailSender.setUsername(this.appEnvironmentVariables.getMailUsername());
        mailSender.setPassword(this.appEnvironmentVariables.getMailPassword());

        // SÓ assim Funcionou!!
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

    @Bean
    public IEmailServiceGateway emailServiceGateway(
            JavaMailSender javaMailSender, IEmailRepositoryGateway emailRepositoryGateway,
            AppEnvironmentVariables appEnvironmentVariables
    ) {
        return new JavaMailServiceGateway(javaMailSender, emailRepositoryGateway, appEnvironmentVariables);
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