package com.novuss.restfulservice.in.dto.response;

import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.in.dto.RefereeInCategoryDto;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record RefereeCategoryResponse(
        String id,
        String title,
        Instant createdAt,
        Instant updatedAt,
        List<RefereeInCategoryDto> referees
) {
}
