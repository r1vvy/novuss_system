package com.novuss.restfulservice.in.util.converter.competitionCategory;

import com.novuss.restfulservice.domain.CompetitionCategory;
import com.novuss.restfulservice.in.dto.request.UpdateCompetitionCategoryInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateCompetitionCategoryInRequestToDomainConverter {
    public static CompetitionCategory convert(UpdateCompetitionCategoryInRequest request) {
        return CompetitionCategory.builder()
                .title(request.title())
                .tagColor(request.tagColor())
                .build();
    }
}
