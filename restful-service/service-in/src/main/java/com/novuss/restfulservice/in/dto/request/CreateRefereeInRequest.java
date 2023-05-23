package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateRefereeInRequest(
        @NotBlank(message = "City must not be blank")
        @Pattern(regexp = "^[\\p{L}0-9 ,.]+$", message = "City should only contain letters, numbers, spaces, commas, and periods")
        String city,
        @NotBlank(message = "Commission number must not be blank")
        @Pattern(regexp = "^[\\p{L}0-9]+$", message = "Commission number should only contain letters and digits")
        String commissionNumber,
        @Valid
        Person person
) {
}
