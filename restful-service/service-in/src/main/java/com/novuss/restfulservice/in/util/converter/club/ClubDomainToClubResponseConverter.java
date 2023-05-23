package com.novuss.restfulservice.in.util.converter.club;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.in.util.converter.location.LocationDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.person.PersonDomainToPersonResponseConverter;
import com.novuss.restfulservice.in.dto.response.ClubResponse;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClubDomainToClubResponseConverter {
    public static ClubResponse convert(Club domain) {
        return ClubResponse.builder()
                .id(domain.id().toString())
                .title(domain.title())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .contactPerson(Optional.ofNullable(
                        domain.contactPerson())
                        .map(PersonDomainToPersonResponseConverter::convert)
                        .orElse(null)
                )
                .location(Optional.ofNullable(
                        domain.location())
                        .map(LocationDomainToResponseConverter::convert)
                        .orElse(null)
                )
                .build();
    }
}
