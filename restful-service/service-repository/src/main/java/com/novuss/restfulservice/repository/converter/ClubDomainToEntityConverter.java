package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClubDomainToEntityConverter {
    public static ClubEntity convert(Club domain) {
        var builder = ClubEntity.builder()
                .id(domain.id())
                .title(domain.title())
                .image(domain.image())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt());

        if(domain.location() != null) {
            var locationEntity = LocationDomainToEntityConverter.convert(domain.location());
            builder.locationEntity(locationEntity);
        }
        if (domain.contactPerson() != null) {
            var contactPersonEntity = PersonDomainToEntityConverter.convert(domain.contactPerson());
            builder.contactPersonEntity(contactPersonEntity);
        }

        return builder.build();
    }
}
