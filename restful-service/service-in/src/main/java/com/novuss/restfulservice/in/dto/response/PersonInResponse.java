package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record PersonInResponse(
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
