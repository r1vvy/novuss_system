package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.core.port.in.token.JwtUseCase;
import com.novuss.authservice.core.port.out.FindUserByUsernamePort;
import com.novuss.authservice.domain.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;


// TODO: change this to security module and use FindUserByUsernameUseCase
@Service
@RequiredArgsConstructor
public class AuthenticateUserByUsernameService implements AuthenticateUserByUsernameUseCase {
    private final AuthenticationManager authenticationManager;
    private final FindUserByUsernamePort findUserByUsernamePort;
    private final JwtUseCase jwtService;

    @Override
    public String authenticateUserByUsername(String username, String password) {
        var user = findUserByUsernamePort.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username = " + username));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        var roles = user.getRoles().stream()
                .map(UserRole::name)
                .toArray(String[]::new);
        var claims = Map.of(
                "id", user.getId(),
                "roles", (Object) roles
        );

        return jwtService.generateToken(claims, user);
    }
}
