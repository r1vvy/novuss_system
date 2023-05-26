package com.novuss.restfulservice.in.util.converter.competition_category;

import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.in.dto.response.CompetitionCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class CompetitionCategoryDomainToResponseConverter {
    public static CompetitionCategoryResponse convert(CompetitionCategory category) {
        return CompetitionCategoryResponse.builder()
                .id(category.id().toString())
                .title(category.title())
                .tagColor(category.tagColor())
                .build();
    }
}
