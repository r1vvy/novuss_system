package com.restfulservice.domain;

import lombok.Builder;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Date;

@Builder
public record Token(
        String token,
        Instant expirationTime
) {
}
