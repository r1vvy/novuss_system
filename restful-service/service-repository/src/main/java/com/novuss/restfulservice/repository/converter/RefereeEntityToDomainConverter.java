package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.stereotype.Component;

@Component
public class RefereeEntityToDomainConverter {
    public static Referee convert(RefereeEntity entity) {
        var builder = Referee.builder()
                .id(entity.getId())
                .commissionNumber(entity.getCommissionNumber())
                .city(entity.getCity())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt());

        if(entity.getPersonEntity() != null) {
            var person = PersonEntityToDomainConverter.convert(entity.getPersonEntity());
            builder.person(person);
        }
        if (entity.getCategoryEntity() != null) {
            var category = RefereeCategoryEntityToDomainConverter.convert(entity.getCategoryEntity());
            builder.category(category);
        }

        return builder.build();
    }
}
