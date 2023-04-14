package com.novuss.authservice.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.novuss.authservice.core.port.in.token.JwtUseCase;
import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.security.exception.InvalidTokenException;
import com.novuss.authservice.security.util.UserRoleDeserializer;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService implements JwtUseCase {
    // TODO: Move to config
    private static final String SECRET_KEY = "4D6251655468576D5A7134743777217A25432A46294A404E635266556A586E32";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        var module = new SimpleModule();
        module.addDeserializer(List.class, new UserRoleDeserializer());
        objectMapper.registerModule(module);
    }

    public String extendExpirationTime(String token) {
        log.debug("Extending expiration time for token: {}", token);
        var claims = getAllClaimsFromToken(token);
        log.debug("Claims: {}", claims);

        var currentTime = System.currentTimeMillis();
        var newExpiration = new Date(currentTime + EXPIRATION_TIME);
        log.debug("New expiration: {}", newExpiration);
        claims.setExpiration(newExpiration);

        try {
            var newToken =  Jwts.builder()
                    .setClaims(claims)
                    .signWith(getSignInKey(), SIGNATURE_ALGORITHM)
                    .compact();
            log.debug("New token: {}", newToken);

            return newToken;
        } catch (InvalidKeyException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw new InvalidTokenException("Invalid JWT signature");
        }

    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("id", String.class));
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SIGNATURE_ALGORITHM)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final var username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        try {
            return getExpirationDateFromToken(token).before(new Date());

        } catch (NullPointerException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new InvalidTokenException("Invalid JWT token");
        }
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Claims getAllClaimsFromToken(String token) {
        try {

            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch(SignatureException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            throw new InvalidTokenException("Invalid JWT signature");
        } catch(MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            throw new InvalidTokenException("Malformed JWT token ");
        } catch(ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            throw new InvalidTokenException("JWT token is expired");
        } catch(UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            throw new InvalidTokenException("JWT token is unsupported");
        } catch(IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
            throw new InvalidTokenException("JWT claims string is empty");
        } catch (DecodingException e) {
            log.warn("Failed to decode JWT claims: {}", e.getMessage());
            throw new InvalidTokenException("JWT claims could not be decoded");
        }
    }

    public List<UserRole> getUserRolesFromToken(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            log.info("Claims: {}", claims);
            return objectMapper.convertValue(claims.get("roles"), new TypeReference<List<UserRole>>() {});
        } catch(io.jsonwebtoken.security.SignatureException e) {
            log.warn("Invalid JWT signature: {}", e.getMessage());
            throw new InvalidTokenException("Invalid JWT signature");
        } catch(MalformedJwtException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            throw new InvalidTokenException("Malformed Jwt token ");
        } catch(ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            throw new InvalidTokenException("JWT token is expired");
        } catch(UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            throw new InvalidTokenException("JWT token is unsupported");
        } catch(IllegalArgumentException e) {
            log.warn("JWT claims string is empty: {}", e.getMessage());
            throw new InvalidTokenException("JWT claims string is empty");
        } catch (DecodingException e) {
            log.warn("Failed to decode JWT claims: {}", e.getMessage());
            throw new InvalidTokenException("JWT claims could not be decoded");
        }
    }

    private Key getSignInKey() {
        try {
            var keyBytes = Decoders.BASE64.decode(SECRET_KEY);

            return Keys.hmacShaKeyFor(keyBytes);
        } catch(DecodingException e) {
            log.warn("Could not decode secret key: {}", e.getMessage());

            throw new InvalidTokenException("Could not decode secret key for JWT");
        }
    }
}
