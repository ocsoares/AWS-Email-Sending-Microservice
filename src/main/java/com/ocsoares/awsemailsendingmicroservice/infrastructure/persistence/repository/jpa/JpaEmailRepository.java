package com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.repository.jpa;

import com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.entity.EmailPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEmailRepository extends JpaRepository<EmailPersistenceEntity, UUID> {
}
