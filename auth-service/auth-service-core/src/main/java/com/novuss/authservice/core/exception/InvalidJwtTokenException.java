package com.novuss.authservice.core.exception;

import io.jsonwebtoken.JwtException;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String invalidJwtToken, JwtException e) {
    }
}
