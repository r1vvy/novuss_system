package com.novuss.restfulservice.in.converter.location;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.in.converter.person.PersonDomainToPersonInResponseConverter;
import com.novuss.restfulservice.in.dto.response.LocationResponse;
import org.springframework.stereotype.Component;

@Component
public class LocationDomainToResponseConverter {
    public static LocationResponse convert(Location domain) {
        return LocationResponse.builder()
                .id(domain.id())
                .title(domain.title())
                .city(domain.city())
                .address(domain.address())
                .latitude(domain.latitude())
                .longitude(domain.longitude())
                .contactPerson(PersonDomainToPersonInResponseConverter.convert(domain.contactPerson()))
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .build();
    }
}
