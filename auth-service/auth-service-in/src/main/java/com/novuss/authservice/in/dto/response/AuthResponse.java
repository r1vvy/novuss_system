package com.novuss.authservice.in.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
