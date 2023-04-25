package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.util.Date;

@Builder
public record LicenceResponse(
        String id,
        String title,
        Date issuedDate,
        Instant createdAt,
        Instant updatedAt
) {
}
