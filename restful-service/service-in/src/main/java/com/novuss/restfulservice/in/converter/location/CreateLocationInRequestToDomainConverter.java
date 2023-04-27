package com.novuss.restfulservice.in.converter.location;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.in.converter.person.CreatePersonInRequestToDomainConverter;
import com.novuss.restfulservice.in.dto.request.CreateLocationInRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateLocationInRequestToDomainConverter {

    public static Location convert(CreateLocationInRequest request) {
        return Location.builder()
                .title(request.title())
                .city(request.city())
                .address(request.address())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .contactPerson(Optional.ofNullable(
                        request.contactPerson())
                        .map(CreatePersonInRequestToDomainConverter::convert)
                        .orElse(null))
                .build();
    }
}
