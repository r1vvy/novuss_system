package com.restfulservice.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

public record ContactInfo(
        UUID id,
        String phoneNumber,
        String email,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}
