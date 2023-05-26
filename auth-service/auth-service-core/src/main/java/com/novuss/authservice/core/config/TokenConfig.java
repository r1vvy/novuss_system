package com.novuss.authservice.core.config;

import org.springframework.stereotype.Component;

@Component
public class TokenConfig {
    public static final long TOKEN_EXPIRY_IN_SECONDS = 1000L * 15 * 60; // 15 minutes
}
