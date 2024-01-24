package com.ocsoares.awsemailsendingmicroservice.domain.entity;

public record EmailDomainEntity(String to, String subject, String body) {
}