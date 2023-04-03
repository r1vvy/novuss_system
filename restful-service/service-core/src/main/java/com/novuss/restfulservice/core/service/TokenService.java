package com.novuss.restfulservice.core.service;

import com.restfulservice.domain.Token;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class TokenService implements TokenUseCase {
    private static final String ALGORITHM = "HS256";
    private static final int KEY_SIZE = 256;
    private static final long EXPIRATION_TIME = 86_400_000; // 1 day in milliseconds

    private final SecretKeySpec secretKeySpec;

    public TokenService() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[KEY_SIZE / 8];
        secureRandom.nextBytes(key);
        secretKeySpec = new SecretKeySpec(key, ALGORITHM);
    }

    @Override
    public Token generateToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plusMillis(EXPIRATION_TIME);

        String tokenString = Jwts.builder()
                .setId(user.id().toString())
                .setSubject(user.username())
                .claim("role", user.role().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .signWith(secretKeySpec)
                .compact();

        log.info("Generated token for user: {}", user.username());

        return Token.builder()
                .token(tokenString)
                .expirationTime(expiration)
                .build();
    }
}
