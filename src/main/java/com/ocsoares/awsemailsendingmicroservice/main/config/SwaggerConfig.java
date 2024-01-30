package com.ocsoares.awsemailsendingmicroservice.main.config;

import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customAPI() {
        return new OpenAPI().info(new Info().title("AWS-Email-Sending-Microservice")
                .version("1.0.0")
                .description(
                        "Um microserviço de enviar email desenvolvido com Arquitetura Limpa, AWS SES & SQS, testes e documentado com Swagger"));
    }

    @Bean
    public OpenApiCustomizer openApiCustomizer() {
        ResolvedSchema errorResponseSchema = ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(ExceptionResponse.class));
        Content content = new Content().addMediaType("application/json",
                new MediaType().schema(errorResponseSchema.schema)
        );
        return openApi -> openApi.getPaths()
                .values()
                .forEach(pathItem -> pathItem.readOperations().forEach(operation -> {
                    ApiResponses responses = operation.getResponses();

                    responses.remove("404");
                    responses.remove("405");
                    responses.remove("501");

                    responses.addApiResponse("202", new ApiResponse().description("Accepted"));
                    responses.addApiResponse("500",
                            new ApiResponse().description("Internal Server Error").content(content)
                    );
                }));
    }
}
