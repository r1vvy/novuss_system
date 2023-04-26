package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationDomainToEntityConverter {
    public static LocationEntity convert(Location domain) {
        var builder = LocationEntity.builder()
                .id(domain.id())
                .address(domain.address())
                .city(domain.city())
                .title(domain.title())
                .latitude(domain.latitude())
                .longitude(domain.longitude())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt());

        if (domain.contactPerson() != null) {
            var personEntity = PersonDomainToEntityConverter.convert(domain.contactPerson());
            builder.personEntity(personEntity);
        }

        return builder.build();
    }
}
