package com.novuss.restfulservice.core.service;

import com.novuss.restfulservice.core.port.in.GetUserIdFromTokenUseCase;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserIdFromTokenService implements GetUserIdFromTokenUseCase {
    private final static String secretKey = "4D6251655468576D5A7134743777217A25432A46294A404E635266556A586E32";

    @Override
    public String getUserIdFromToken(String token) {
        var jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();

        var claims = jwtParser.parseClaimsJws(token).getBody();

        return claims.get("id", String.class);
    }
}
