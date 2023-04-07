package com.novuss.restfulservice.in.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PersonDto(
        String firstName,
        String lastName,
        String personCode,
        LocalDate birthDay
) {
}
