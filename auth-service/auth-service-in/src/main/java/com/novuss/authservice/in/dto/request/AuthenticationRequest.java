package com.novuss.authservice.in.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotBlank(message = "Username cannot be blank")
        String username,
        @NotBlank(message = "Password cannot be blank")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$\n",
                message = "Password must be at least 8 characters long and " +
                        "contain at least one uppercase letter, " +
                        "one lowercase letter and one number")
        String password
) {
}
