package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExtendTokenExpiryService implements ExtendTokenExpiryUseCase {
    private final JwtService jwtService;
    @Override
    public String extendTokenExpiry(String token) {
        log.info("Extending token expiry");

        return jwtService.extendExpirationTime(token);
    }
}