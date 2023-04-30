package com.novuss.restfulservice.domain;


import lombok.Builder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Builder
public record ErrorResponse(
        LocalDateTime timestamp,
        String type,
        String title,
        int status,
        List<String> detail,
        String instance
) {
    public ErrorResponse {
        if (timestamp == null) {
            timestamp = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        }
    }
}
