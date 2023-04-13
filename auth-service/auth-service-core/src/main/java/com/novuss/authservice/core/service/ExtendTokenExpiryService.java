package com.novuss.authservice.core.service;

import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.token.JwtUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// TODO: change this to security module
@Service
@RequiredArgsConstructor
@Slf4j
public class ExtendTokenExpiryService implements ExtendTokenExpiryUseCase {
    private final JwtUseCase jwtService;
    @Override
    public String extendTokenExpiry(String token) {
        return jwtService.extendExpirationTime(token);
    }
}
