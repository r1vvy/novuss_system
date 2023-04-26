package com.novuss.restfulservice.in.converter.club;

import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.in.converter.location.LocationDomainToResponseConverter;
import com.novuss.restfulservice.in.converter.person.PersonDomainToPersonResponseConverter;
import com.novuss.restfulservice.in.dto.response.ClubResponse;
import org.springframework.stereotype.Component;

@Component
public class ClubDomainToClubResponseConverter {
    public static ClubResponse convert(Club domain) {
        return ClubResponse.builder()
                .id(domain.id().toString())
                .title(domain.title())
                .image(domain.image())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .contactPerson(PersonDomainToPersonResponseConverter.convert(domain.contactPerson()))
                .location(LocationDomainToResponseConverter.convert(domain.location()))
                .build();
    }
}
