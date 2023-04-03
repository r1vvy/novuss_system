package com.novuss.authservice.domain;

import lombok.Builder;

import java.time.Instant;

@Builder
public record Token(
        String token,
        Instant expirationTime
) {
}
