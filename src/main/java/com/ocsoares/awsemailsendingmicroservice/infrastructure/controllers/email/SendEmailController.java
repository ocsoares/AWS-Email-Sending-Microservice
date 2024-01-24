package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email;

import com.ocsoares.awsemailsendingmicroservice.application.usecases.email.SendEmailUseCase;
import com.ocsoares.awsemailsendingmicroservice.application.usecases.response.EmailResponse;
import com.ocsoares.awsemailsendingmicroservice.domain.entity.EmailDomainEntity;
import com.ocsoares.awsemailsendingmicroservice.domain.exceptions.response.ExceptionResponse;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos.EmailDTO;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.mappers.EmailControllerMapper;
import com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.interfaces.IControllerWithArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// Mudar isso tudo pq era do User !!
@RestController
@RequiredArgsConstructor
public class SendEmailController implements IControllerWithArgument<EmailResponse, EmailDTO, Exception> {
    private final SendEmailUseCase sendEmailUseCase;
    private final EmailControllerMapper emailControllerMapper;

    @Override
    @Operation(summary = "Send an email", tags = "email")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "500")
    @SecurityRequirement(name = "none")
    @PostMapping("email")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public EmailResponse handle(@RequestBody @Valid EmailDTO emailDTO) throws Exception {
        EmailDomainEntity emailDomainEntity = this.emailControllerMapper.toDomain(emailDTO);

        return this.sendEmailUseCase.execute(emailDomainEntity);
    }
}
