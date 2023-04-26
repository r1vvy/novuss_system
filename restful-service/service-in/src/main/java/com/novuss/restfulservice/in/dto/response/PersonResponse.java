package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;

@Builder(toBuilder = true)
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
