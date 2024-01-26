package com.ocsoares.awsemailsendingmicroservice.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "emails")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Data
public class EmailPersistenceEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "to_recipient")
    @NonNull
    private String toRecipient;

    @Column(nullable = false)
    @NonNull
    private String subject;

    @Column(nullable = false)
    @NonNull
    private String body;
}
