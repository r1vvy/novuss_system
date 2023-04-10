package com.novuss.restfulservice.in.converter.refereeCategory;

import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.in.dto.response.RefereeCategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class RefereeCategoryDomainToRefereeCategoryResponseConverter {
    public static RefereeCategoryResponse convert (RefereeCategory category) {
        return RefereeCategoryResponse.builder()
                .id(category.id().toString())
                .title(category.title())
                .createdAt(category.createdAt())
                .updatedAt(category.updatedAt())
                .build();
    }
}
