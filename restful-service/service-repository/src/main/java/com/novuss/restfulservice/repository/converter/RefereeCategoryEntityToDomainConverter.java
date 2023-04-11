package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RefereeCategoryEntityToDomainConverter {

    public static RefereeCategory convert(RefereeCategoryEntity entity) {
        var builder = RefereeCategory.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if (entity.getReferees() != null) {
            builder.referees(entity.getReferees()
                    .stream()
                    .map(RefereeCategoryEntityToDomainConverter::convertRefereeEntityToDomain)
                    .collect(Collectors.toSet())
            );
        }

        return builder.build();
    }

    private static Referee convertRefereeEntityToDomain(RefereeEntity entity) {
        return Referee.builder()
                .id(entity.getId())
                .person(PersonEntityToDomainConverter.convert(entity.getPersonEntity()))
                .commissionNumber(entity.getCommissionNumber())
                .city(entity.getCity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
