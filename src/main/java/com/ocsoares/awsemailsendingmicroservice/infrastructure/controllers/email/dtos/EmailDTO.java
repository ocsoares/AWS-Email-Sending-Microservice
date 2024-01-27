package com.ocsoares.awsemailsendingmicroservice.infrastructure.controllers.email.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(example = "{\"toRecipient\":\"johndoe@example.com\",\"subject\":\"Any subject\",\"body\":\"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.\"}")
public record EmailDTO(
        @NotBlank(message = "The toRecipient is required") @Email(message = "Must be a valid email address") String toRecipient,
        @NotBlank(message = "The subject is required") String subject,
        @NotBlank(message = "The body is required") String body) {
}
