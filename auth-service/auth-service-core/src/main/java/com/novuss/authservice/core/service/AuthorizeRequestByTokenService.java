package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.security.JwtService;
import com.novuss.authservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizeRequestByTokenService implements AuthorizeRequestByTokenUseCase {
    private final JwtService jwtService;

    @Override
    public boolean authorize(String token, List<UserRole> requiredAuthorities) {
        log.info("Authorizing request with token: {}", token);
        var claims = jwtService.getAllClaimsFromToken(token);
        var userRoles = UserRole.valueOf(claims.get("roles", String.class));
        var isAuthorized = requiredAuthorities.contains(userRoles);

        if (!isAuthorized) {
            log.warn("User is not authorized to perform this action");
            throw new RuntimeException("User is not authorized to perform action");
        }

        return isAuthorized;
    }
}
