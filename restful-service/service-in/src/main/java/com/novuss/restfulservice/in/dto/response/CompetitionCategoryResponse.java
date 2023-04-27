package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record CompetitionCategoryResponse(
        String id,
        String title,
        String tagColor
) {
}
