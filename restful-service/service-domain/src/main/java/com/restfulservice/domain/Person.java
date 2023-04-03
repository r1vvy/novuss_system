package com.restfulservice.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

public record Person(
      UUID id,
      String firstName,
      String lastName,
      String personCode,
      LocalDate birthDay,
      ZonedDateTime createdAt,
      ZonedDateTime updatedAt,
      ContactInfo contactInfo
) {
}
