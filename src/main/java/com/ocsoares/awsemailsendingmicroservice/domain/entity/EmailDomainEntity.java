package com.ocsoares.awsemailsendingmicroservice.domain.entity;

public record EmailDomainEntity(String toRecipient, String subject, String body) {
}