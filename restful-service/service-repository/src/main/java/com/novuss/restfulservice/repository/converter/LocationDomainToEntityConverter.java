package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LocationDomainToEntityConverter {
    public static LocationEntity convert(Location domain) {
        return LocationEntity.builder()
                .id(UUID.fromString(domain.id()))
                .title(domain.title())
                .latitude(domain.latitude())
                .longitude(domain.longitude())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
