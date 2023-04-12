package com.novuss.restfulservice.in.converter.refereeCategory;

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
                .referees("/api/v1/referees/categories?id=" + category.id())
                .build();
    }
}
