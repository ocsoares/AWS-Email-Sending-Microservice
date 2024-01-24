package com.ocsoares.advancedcrudspringboot.infrastructure.persistence.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(unique = true, nullable = false)
    @NonNull
    private String email;

    @Column(nullable = false)
    @NonNull
    private String password;
}
