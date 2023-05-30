package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.entity.CompetitionEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionDomainToEntityConverter {
    public static CompetitionEntity convert(Competition domain) {
        var builder = CompetitionEntity.builder()
                .id(domain.id())
                .title(domain.title())
                .registrationStart(domain.registrationStart())
                .registrationEnd(domain.registrationEnd())
                .competitionStart(domain.competitionStart())
                .competitionEnd(domain.competitionEnd())
                .createdAt(domain.createdAt())
                .updatedAt(domain.updatedAt());

        if(domain.category() != null) {
            builder.competitionCategoryEntity(CompetitionCategoryDomainToEntityConverter.convert(domain.category()));
        }

        if(domain.contactPerson() != null) {
            builder.contactPersonEntity(PersonDomainToEntityConverter.convert(domain.contactPerson()));
        }

        if(domain.location() != null) {
            builder.locationEntity(LocationDomainToEntityConverter.convert(domain.location()));
        }

        return builder.build();
    }
}
