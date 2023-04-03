package com.novuss.authservice.in.dto.response;

import lombok.Builder;

import java.time.Instant;

@Builder
public record AuthenticationResponse(
        String token
) {
}
