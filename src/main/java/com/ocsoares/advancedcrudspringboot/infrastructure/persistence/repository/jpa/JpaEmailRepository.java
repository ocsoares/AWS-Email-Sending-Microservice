package com.ocsoares.advancedcrudspringboot.infrastructure.persistence.repository.jpa;

import com.ocsoares.advancedcrudspringboot.infrastructure.persistence.entity.EmailPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaEmailRepository extends JpaRepository<EmailPersistenceEntity, UUID> {
}
