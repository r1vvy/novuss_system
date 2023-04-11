package com.novuss.restfulservice.in.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
public record RefereeCategoryDto(
        String id,
        String title,
        Instant createdAt,
        Instant updatedAt,
        String referees
) {}
