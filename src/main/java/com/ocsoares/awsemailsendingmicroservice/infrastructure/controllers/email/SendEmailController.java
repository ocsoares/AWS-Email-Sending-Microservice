package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos.EmailDTO;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.interfaces.IControllerWithArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// Usando o "RequiredArgsConstructor" faz INJETAR (Autowired) pelo CONSTRUCTOR da Classe, se estiver usando um "Stereotype"
// como o "Service", por exemplo!!!
@RequiredArgsConstructor
@RestController
public class SendEmailController implements IControllerWithArgument<Void, EmailDTO, Exception> {
    private final AmazonSNS amazonSNS;
    private final Topic snsCatalogTopic;

    @Override
    @Operation(summary = "Send an email", tags = "email")
    @ApiResponse(responseCode = "202")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500")
    @PostMapping("email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Void handle(@RequestBody @Valid EmailDTO emailDTO) {
        this.amazonSNS.publish(this.snsCatalogTopic.getTopicArn(), emailDTO.toString());

        return null;
    }
}
