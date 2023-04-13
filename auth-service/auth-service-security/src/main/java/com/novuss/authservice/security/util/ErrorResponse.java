package com.novuss.authservice.security.util;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
        HttpStatus code,
        String message
) {
}
