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
    @Value("${EMAIL_SOURCE}")
    private String emailSource;
}
