package com.novuss.authservice.core.port.in.token;

public interface ExtendTokenExpiryUseCase {
    String extendTokenExpiry(String token);
}
