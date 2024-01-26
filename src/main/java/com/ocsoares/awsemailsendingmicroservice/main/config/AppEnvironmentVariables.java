package com.ocsoares.awsemailsendingmicroservice.main.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

// TENTAR mudar a Mensagem de ERRO quando as Variáveis de Env. NÃO EXISTEM !!!

@Validated
@Getter
@Component
public class AppEnvironmentVariables {
    @NotBlank
    @Value("${ONLY_TEST}") // TIRAR do application.properties !!!!
    private String onlyTest; // REMOVER ISSO DEPOIS, claro...
}
