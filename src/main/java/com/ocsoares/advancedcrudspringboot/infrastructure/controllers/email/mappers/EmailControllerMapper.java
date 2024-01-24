package com.ocsoares.advancedcrudspringboot.infrastructure.controllers.user.mappers;

import com.ocsoares.advancedcrudspringboot.domain.entity.EmailDomainEntity;
import com.ocsoares.advancedcrudspringboot.infrastructure.controllers.user.dtos.EmailDTO;

public class EmailControllerMapper {
    public EmailDomainEntity toDomain(EmailDTO emailDTO) {
        return new EmailDomainEntity(emailDTO.name(), emailDTO.email(), emailDTO.password());
    }
}
