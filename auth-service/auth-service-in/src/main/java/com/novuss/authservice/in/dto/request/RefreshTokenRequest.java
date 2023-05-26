package com.novuss.authservice.in.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RefreshTokenRequest(
        @Pattern(regexp = "\\S+", message = "Token is required")
        String token
) {
}
