package com.novuss.restfulservice.core.port.in;

public interface TokenValidationUseCase {
    boolean validateToken(String token);
}
