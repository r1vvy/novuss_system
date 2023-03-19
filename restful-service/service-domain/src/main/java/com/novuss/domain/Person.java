package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record Person(
      UUID id,
      String firstName,
      String lastName,
      String personCode,
      LocalDate birthDay,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      ContactInfo contactInfo
) {
}
