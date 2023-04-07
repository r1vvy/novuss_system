package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.TokenValidationUseCase;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TokenValidationService implements TokenValidationUseCase {
    // TODO: add this to properties file
    private final static String secretKey = "4D6251655468576D5A7134743777217A25432A46294A404E635266556A586E32";

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException exc) {
            log.error("Invalid token: {}", token);
            throw exc;
        }

        return true;
    }

}
