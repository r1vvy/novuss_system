package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CreatePersonInRequest(
        @NotBlank(message = "First name must not be blank")
        @Pattern(regexp = "^[\\p{L} ]+$", message = "First name should only contain letters and spaces")
        String firstName,
        @NotBlank(message = "Last name must not be blank")
        @Pattern(regexp = "^[\\p{L} ]+$", message = "Last name should only contain letters and spaces")
        String lastName,
        @NotBlank(message = "Email must not be blank")
        @Email(message = "Email must be a valid email address")
        String email,
        @NotBlank(message = "Phone number must not be blank")
        @Pattern(regexp = "^[0-9 +()-]+$", message = "Phone number should only contain digits, spaces, parentheses, hyphens, and plus signs")
        String phoneNumber,
        @Past(message = "Birth date must be in the past")
        LocalDate birthDay
) {
}
