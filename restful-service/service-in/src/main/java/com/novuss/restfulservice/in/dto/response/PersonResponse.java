package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public record PersonResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDay,
        Instant createdAt,
        Instant updatedAt
) {
}
