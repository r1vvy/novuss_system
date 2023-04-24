package com.novuss.authservice.in.dto.request;

import com.novuss.authservice.domain.UserRole;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

public record CreateUserInRequest(
        @NotBlank(message = "Username cannot be blank")
        @NotEmpty(message = "Username cannot be empty")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,

        @NotNull(message = "Roles cannot be null")
        @NotEmpty(message = "Roles cannot be empty")
        Set<UserRole> roles
) {
}
