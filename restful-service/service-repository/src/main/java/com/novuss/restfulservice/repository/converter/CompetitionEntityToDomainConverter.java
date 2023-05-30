package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Competition;
import com.novuss.restfulservice.repository.entity.CompetitionEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionEntityToDomainConverter {
    public static Competition convert(CompetitionEntity entity) {
        var builder = Competition.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .competitionStart(entity.getCompetitionStart())
                .competitionEnd(entity.getCompetitionEnd())
                .registrationStart(entity.getRegistrationStart())
                .registrationEnd(entity.getRegistrationEnd())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if(entity.getCompetitionCategoryEntity() != null) {
            builder.category(CompetitionCategoryEntityToDomainConverter.convert(entity.getCompetitionCategoryEntity()));
        }

        if (entity.getContactPersonEntity() != null) {
            builder.contactPerson(PersonEntityToDomainConverter.convert(entity.getContactPersonEntity()));
        }

        if(entity.getLocationEntity() != null) {
            builder.location(LocationEntityToDomainConverter.convert(entity.getLocationEntity()));
        }

        return builder.build();
    }
}
