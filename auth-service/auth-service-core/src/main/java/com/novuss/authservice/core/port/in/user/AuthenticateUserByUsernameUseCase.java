package com.novuss.authservice.core.port.in.user;


public interface AuthenticateUserByUsernameUseCase {
    String authenticateUserByUsername(String username, String password);
}
