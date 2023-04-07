package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record CreatePersonInRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDay
) {

}
