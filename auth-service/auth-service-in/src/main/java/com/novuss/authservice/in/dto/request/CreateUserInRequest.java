package com.novuss.authservice.in.dto.request;

import com.novuss.authservice.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

public record CreateUserInRequest(
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotNull(message = "Roles cannot be null")
        Set<UserRole> roles
) {
}
