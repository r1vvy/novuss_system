package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.core.port.in.token.JwtUseCase;
import com.novuss.authservice.core.port.out.FindUserByUsernamePort;
import com.novuss.authservice.domain.User;
import com.novuss.authservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticateUserByUsernameService implements AuthenticateUserByUsernameUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtUseCase jwtService;

    @Override
    public String authenticateUserByUsername(String username, String password) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        var authorities = auth.getAuthorities();
        var roles = authorities.stream()
                .map(Object::toString)
                .map(UserRole::valueOf)
                .toArray(UserRole[]::new);

        var principal = auth.getPrincipal();
        var userId = ((User) principal).getId();
        var claims = Map.of(
                "id", userId,
                "roles", (Object) roles
        );

        return jwtService.generateToken(claims, (UserDetails) principal);
    }
}
