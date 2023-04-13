package com.novuss.authservice.security.exception;

import io.jsonwebtoken.JwtException;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String invalidJwtToken, JwtException e) {
        super(invalidJwtToken, e);
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
