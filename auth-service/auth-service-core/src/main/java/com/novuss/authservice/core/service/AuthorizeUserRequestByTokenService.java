package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.token.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.token.JwtUseCase;
import com.novuss.authservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: change this to security module
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserRequestByTokenService implements AuthorizeRequestByTokenUseCase {
    private final JwtUseCase jwtService;

    @Override
    public boolean authorize(String token, List<UserRole> requiredAuthorities) {
        log.info("Required authorities: {}", requiredAuthorities);
        log.info("Authorizing request with token: {}", token);
        var userRoles = jwtService.getUserRolesFromToken(token);
        log.info("Extracted user roles from token: {}", userRoles);

        var isAuthorized = requiredAuthorities.stream()
                .anyMatch(userRoles::contains);

        if (!isAuthorized) {
            log.warn("User is not authorized to perform this action");
            throw new AccessDeniedException("User is not authorized to perform this action");
        }
        log.info("User is authorized to perform this action");

        return isAuthorized;
    }
}
