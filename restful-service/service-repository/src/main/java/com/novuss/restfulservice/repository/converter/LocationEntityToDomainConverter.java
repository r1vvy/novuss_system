package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import org.springframework.stereotype.Component;

@Component
public class LocationEntityToDomainConverter {

    public static Location convert(LocationEntity entity) {
        return Location.builder()
                .id(entity.getId().toString())
                .title(entity.getTitle())
                .address(entity.getAddress())
                .city(entity.getCity())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
