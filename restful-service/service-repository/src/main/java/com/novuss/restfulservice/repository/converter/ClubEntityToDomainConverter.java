package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import org.springframework.stereotype.Component;

@Component
public class ClubEntityToDomainConverter {
    public static Club convert(ClubEntity entity) {
        var builder = Club.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .image(entity.getImage())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if(entity.getLocationEntity() != null) {
            var location = LocationEntityToDomainConverter.convert(entity.getLocationEntity());
            builder.location(location);
        }

        if (entity.getContactPersonEntity() != null) {
            var contactPerson = PersonEntityToDomainConverter.convert(entity.getContactPersonEntity());
            builder.contactPerson(contactPerson);
        }

        return builder.build();
    }
}
