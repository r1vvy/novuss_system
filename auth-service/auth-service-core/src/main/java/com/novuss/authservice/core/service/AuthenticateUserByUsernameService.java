package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.core.port.out.FindUserByUsernamePort;
import com.novuss.authservice.core.security.JwtService;
import com.novuss.authservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateUserByUsernameService implements AuthenticateUserByUsernameUseCase {
    private final AuthenticationManager authenticationManager;
    private final FindUserByUsernamePort findUserByUsernamePort;
    private final JwtService jwtService;

    @Override
    public String authenticateUserByUsername(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        var user = findUserByUsernamePort.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username = " + username));

        var claims = Map.of(
                "id", user.getId(),
                "roles", user.getRoles()
        );

        return jwtService.generateToken(claims, user);
    }
}
