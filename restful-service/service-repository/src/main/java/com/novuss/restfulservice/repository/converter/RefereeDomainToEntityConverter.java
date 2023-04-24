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
                .commissionNumber(referee.commissionNumber())
                .city(referee.city())
                .createdAt(referee.createdAt())
                .updatedAt(referee.updatedAt())
                .build();
    }
}
