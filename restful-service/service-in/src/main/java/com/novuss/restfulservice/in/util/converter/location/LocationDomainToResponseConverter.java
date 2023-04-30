package com.novuss.restfulservice.in.util.converter.location;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.in.util.converter.person.PersonDomainToPersonResponseConverter;
import com.novuss.restfulservice.in.dto.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class LocationDomainToResponseConverter {
    public static LocationResponse convert(Location domain) {
        var builder = LocationResponse.builder()
                .id(domain.id().toString())
                .title(domain.title())
                .city(domain.city())
                .address(domain.address())
                .latitude(domain.latitude())
                .longitude(domain.longitude())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .contactPerson(Optional.ofNullable(domain.contactPerson())
                        .map(PersonDomainToPersonResponseConverter::convert)
                        .orElse(null));

        return builder.build();
    }
}
