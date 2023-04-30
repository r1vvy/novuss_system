package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder(toBuilder = true)
public record FileResponse(
        String id,
        String title,
        String type,
        String location,
        long size,
        Instant createdAt,
        Instant updatedAt
) {
}
