package com.novuss.restfulservice.in.util.converter.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.in.dto.request.CreateCompetitionCategoryInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateCompetitionCategoryInRequestToDomainConverter {
    public static CompetitionCategory convert(CreateCompetitionCategoryInRequest request) {
        return CompetitionCategory.builder()
                .title(request.title())
                .tagColor(request.tagColor())
                .build();
    }
}
