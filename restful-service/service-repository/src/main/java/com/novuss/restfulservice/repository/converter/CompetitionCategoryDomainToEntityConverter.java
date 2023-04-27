package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.repository.entity.CompetitionCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CompetitionCategoryDomainToEntityConverter {
    public static CompetitionCategoryEntity convert(CompetitionCategory domain) {
        return CompetitionCategoryEntity.builder()
                .id(domain.id())
                .title(domain.title())
                .tagColor(domain.tagColor())
                .build();
    }
}
