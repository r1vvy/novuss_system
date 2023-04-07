package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UpdatePersonInRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDay
) {
}
