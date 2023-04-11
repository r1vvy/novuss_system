package com.novuss.restfulservice.in.dto;

import com.novuss.restfulservice.domain.Person;
import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Builder
public record RefereeInCategoryDto(
    String id,
    String firstName,
    String lastName,
    LocalDate birthDate,
    String city,
    String email,
    String phoneNumber,
    String commissionNumber
) {
}
