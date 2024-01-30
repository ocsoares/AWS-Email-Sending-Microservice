package com.ocsoares.awsemailsendingmicroservice.main.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Component
public class AppEnvironmentVariables {
    @NotBlank
    @Value("${DB_NAME}")
    private String dbName;

    @NotBlank
    @Value("${DB_URL}")
    private String dbUrl;

    @NotBlank
    @Value("${DB_USER}")
    private String dbUser;

    @NotBlank
    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @NotBlank
    @Value("${EMAIL_SOURCE}")
    private String emailSource;

    @NotBlank
    @Value("${AWS_REGION}")
    private String awsRegion;

    @NotBlank
    @Value("${AWS_ACCESS_KEY_ID}")
    private String awsAccessKeyId;

    @NotBlank
    @Value("${AWS_SECRET_KEY}")
    private String awsSecretKey;

    @NotBlank
    @Value("${AWS_SNS_TOPIC_ARN}")
    private String awsSnsTopicArn;

    @NotBlank
    @Value("${MAIL_HOST}")
    private String mailHost;

    @NotBlank
    @Value("${MAIL_PORT}")
    private String mailPort;

    @NotBlank
    @Value("${MAIL_USERNAME}")
    private String mailUsername;

    @NotBlank
    @Value("${MAIL_PASSWORD}")
    private String mailPassword;
}
