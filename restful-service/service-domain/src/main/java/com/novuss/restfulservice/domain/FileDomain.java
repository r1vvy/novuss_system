package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record FileDomain(
        UUID id,
        String title,
        String type,
        String location,
        long size,
        byte[] content,
        Instant createdAt,
        Instant updatedAt
) {
}
