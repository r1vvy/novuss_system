package com.novuss.authservice.core.port.in.token;


public interface AuthenticateUserByUsernameUseCase {
    String authenticateUserByUsername(String username, String password);
}
