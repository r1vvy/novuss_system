package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RefereeCategoryEntityToDomainConverter {

        public static RefereeCategory convert(RefereeCategoryEntity entity) {

            return RefereeCategory.builder()
                    .id(entity.getId().toString())
                    .createdAt(entity.getCreatedAt())
                    .updatedAt(entity.getUpdatedAt())
                    .title(entity.getTitle())
                    .dateIssued(entity.getDateIssued())
                    .referees(entity.getReferees()
                            .stream()
                            .map(RefereeEntityToDomainConverter::convert)
                            .collect(Collectors.toSet())
                    )
                    .build();
        }
}
