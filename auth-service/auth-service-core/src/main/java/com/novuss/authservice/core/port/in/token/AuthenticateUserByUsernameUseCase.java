package com.novuss.authservice.core.port.in.token;

import com.novuss.authservice.domain.Token;
import com.novuss.authservice.domain.User;

public interface AuthenticateUserByUsernameUseCase {
    String authenticateUserByUsername(String username, String password);
}
