package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;


@Builder
public record UpdatePlayerInRequest(
        @Min(value = 1000, message = "Rating must be at least 1000")
        Integer rating,
        String gender
) {
}
