package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.stereotype.Component;

@Component
public class RefereeEntityToDomainConverter {
    public static Referee convert(RefereeEntity entity) {
        return Referee.builder()
                .id(entity.getId().toString())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .person(PersonEntityToDomainConverter.convert(entity.getPersonEntity()))
                .category(RefereeCategoryEntityToDomainConverter.convert(entity.getCategory()))
                .commissionNumber(entity.getCommissionNumber())
                .city(entity.getCity())
                .build();
    }
}