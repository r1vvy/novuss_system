package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

public record ContactInfo(
        UUID id,
        String phoneNumber,
        String email,
        LocalDate createdAt,
        LocalDate updatedAt
) {}
