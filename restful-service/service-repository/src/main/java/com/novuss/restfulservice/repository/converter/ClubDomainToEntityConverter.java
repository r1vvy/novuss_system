package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClubDomainToEntityConverter {
    public static ClubEntity convert(Club domain) {
        return ClubEntity.builder()
                .id(UUID.fromString(domain.id()))
                .title(domain.title())
                .image(domain.image())
                .locationEntity(LocationDomainToEntityConverter.convert(domain.location()))
                .contactPersonEntity(PersonDomainToEntityConverter.convert(domain.contactPerson()))
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
