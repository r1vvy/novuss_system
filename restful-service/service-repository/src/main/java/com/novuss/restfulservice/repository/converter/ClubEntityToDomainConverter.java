package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import org.springframework.stereotype.Component;

@Component
public class ClubEntityToDomainConverter {
    public static Club convert(ClubEntity entity) {
        return Club.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .image(entity.getImage())
                .location(LocationEntityToDomainConverter.convert(entity.getLocationEntity()))
                .contactPerson(PersonEntityToDomainConverter.convert(entity.getContactPersonEntity()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
