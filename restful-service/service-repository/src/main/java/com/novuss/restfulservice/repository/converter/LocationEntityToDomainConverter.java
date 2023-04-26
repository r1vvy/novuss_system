package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LocationEntityToDomainConverter {

    public static Location convert(LocationEntity entity) {
        var builder = Location.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .address(entity.getAddress())
                .city(entity.getCity())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if (entity.getPersonEntity() != null) {
            var person = PersonEntityToDomainConverter.convert(entity.getPersonEntity());
            builder.contactPerson(person);
        }

        return builder.build();
    }
}
