package com.novuss.restfulservice.in.util.converter.competition;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.in.dto.response.CompetitionResponse;
import com.novuss.restfulservice.in.util.converter.competition_category.CompetitionCategoryDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.location.LocationDomainToResponseConverter;
import com.novuss.restfulservice.in.util.converter.person.PersonDomainToPersonResponseConverter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CompetitionDomainToResponseConverter {
    public static CompetitionResponse convert(Competition domain) {
        return CompetitionResponse.builder()
                .id(domain.id().toString())
                .title(domain.title())
                .registrationStart(domain.registrationStart())
                .registrationEnd(domain.registrationEnd())
                .competitionStart(domain.competitionStart())
                .competitionEnd(domain.competitionEnd())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt())
                .location(Optional.ofNullable(domain.location())
                        .map(LocationDomainToResponseConverter::convert)
                        .orElse(null))
                .category(Optional.ofNullable(domain.category())
                        .map(CompetitionCategoryDomainToResponseConverter::convert)
                        .orElse(null))
                .contactPerson(Optional.ofNullable(domain.contactPerson())
                        .map(PersonDomainToPersonResponseConverter::convert)
                        .orElse(null))
                .build();

    }
}
