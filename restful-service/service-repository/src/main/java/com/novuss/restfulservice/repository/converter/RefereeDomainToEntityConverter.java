package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.stereotype.Component;

@Component
public class RefereeDomainToEntityConverter {
    public static RefereeEntity convert(Referee referee) {
        return RefereeEntity.builder()
                .id(referee.id())
                .personEntity(PersonDomainToEntityConverter.convert(referee.person()))
                .category(RefereeCategoryDomainToEntityConverter.convert(referee.category()))
                .commissionNumber(referee.commissionNumber())
                .city(referee.city())
                .createdAt(referee.createdAt())
                .updatedAt(referee.updatedAt())
                .build();
    }
}
