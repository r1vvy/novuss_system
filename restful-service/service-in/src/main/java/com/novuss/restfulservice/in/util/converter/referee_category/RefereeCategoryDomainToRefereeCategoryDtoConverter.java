package com.novuss.restfulservice.in.util.converter.referee_category;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.in.dto.RefereeCategoryDto;
import org.springframework.stereotype.Component;

@Component
public class RefereeCategoryDomainToRefereeCategoryDtoConverter {

    public static RefereeCategoryDto convert(RefereeCategory category) {
        return RefereeCategoryDto.builder()
                .id(category.id().toString())
                .title(category.title())
                .createdAt(category.createdAt())
                .updatedAt(category.updatedAt())
                .referees("/api/v1/referees/categories/" + category.id())
                .build();
    }
}
