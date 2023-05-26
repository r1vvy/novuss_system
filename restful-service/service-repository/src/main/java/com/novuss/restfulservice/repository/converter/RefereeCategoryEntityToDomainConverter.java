package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class RefereeCategoryEntityToDomainConverter {

    public static RefereeCategory convert(RefereeCategoryEntity entity) {
        return RefereeCategory.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
