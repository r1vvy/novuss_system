package com.novuss.restfulservice.domain;

import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record Person(
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
