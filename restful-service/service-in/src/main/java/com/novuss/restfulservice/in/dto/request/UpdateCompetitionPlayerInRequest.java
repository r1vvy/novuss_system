package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Min;

public record UpdateCompetitionPlayerInRequest(
        String playerId,
        @Min(value = 1, message = "Placement must be a positive integer")
        Integer placement,
        @Min(value = -1000, message = "Rating change must be greater than or equal to -1000")
        Integer ratingChange
) {
}
