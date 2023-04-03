package com.novuss.authservice.in.dto.request;

import com.novuss.authservice.domain.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

public record CreateUserInRequest(
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotBlank(message = "email cannot be blank")
        @Email(message = "email must be valid")
        String email,
        @NotBlank(message = "password cannot be blank")
        String password,

        @NotNull(message = "roles cannot be null")
        Set<UserRole> roles
) {
}
