package com.novuss.restfulservice.in.util.converter.referee_category;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.in.util.converter.referee.RefereeDomainToRefereeInCategoryDtoConverter;
import com.novuss.restfulservice.in.dto.response.RefereeCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class RefereeCategoryDomainToRefereeCategoryResponseConverter {
    public static RefereeCategoryResponse convert (RefereeCategory category) {
        var builder = RefereeCategoryResponse.builder()
                .id(category.id().toString())
                .title(category.title())
                .createdAt(category.createdAt())
                .updatedAt(category.updatedAt());

        if (category.referees() != null) {
            builder.referees(category.referees()
                    .stream()
                    .map(RefereeDomainToRefereeInCategoryDtoConverter::convert)
                    .toList()
            );
        }

        return builder.build();
    }
}
