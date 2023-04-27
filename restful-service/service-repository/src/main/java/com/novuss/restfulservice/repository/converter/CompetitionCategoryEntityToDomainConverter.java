package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.entity.CompetitionCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionCategoryEntityToDomainConverter {

    public static CompetitionCategory convert(CompetitionCategoryEntity entity) {
        return CompetitionCategory.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .build();
    }
}
