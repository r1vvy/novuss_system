package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefereeDomainToEntityConverter {
    public static RefereeEntity convert(Referee referee) {
        return RefereeEntity.builder()
                .id(referee.id())
                .personEntity(PersonDomainToEntityConverter.convert(referee.person()))
                .category(convertRefereeCategoryDomainToEntity(referee.category()))
                .commissionNumber(referee.commissionNumber())
                .city(referee.city())
                .createdAt(referee.createdAt())
                .updatedAt(referee.updatedAt())
                .build();
    }

    private static RefereeCategoryEntity convertRefereeCategoryDomainToEntity(RefereeCategory category) {
        return RefereeCategoryEntity.builder()
                .id(category.id())
                .title(category.title())
                .createdAt(category.createdAt())
                .updatedAt(category.updatedAt())
                .build();
    }
}
