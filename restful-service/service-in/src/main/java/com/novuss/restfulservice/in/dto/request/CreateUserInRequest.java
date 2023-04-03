package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.ZonedDateTime;
import java.util.UUID;

public record CreateUserInRequest(
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotBlank(message = "email cannot be blank")
        @Email(message = "email must be valid")
        String email,
        @NotBlank(message = "password cannot be blank")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$\n",
                message = "password must be at least 8 characters long and " +
                        "contain at least one uppercase letter, " +
                        "one lowercase letter and one number")
        String password
) {
}
