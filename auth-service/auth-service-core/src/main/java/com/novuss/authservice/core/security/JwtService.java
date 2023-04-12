package com.novuss.authservice.core.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.novuss.authservice.core.util.UserRoleDeserializer;
import com.novuss.authservice.domain.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    // TODO: Move to config
    private static final String SECRET_KEY = "4D6251655468576D5A7134743777217A25432A46294A404E635266556A586E32";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new UserRoleDeserializer());
        objectMapper.registerModule(module);
    }

    public String extendExpirationTime(String token) {
        token = token.replace("Bearer ", "");
        log.debug("Extending expiration time for token: {}", token);
        var claims = getAllClaimsFromToken(token);
        log.debug("Claims: {}", claims);
        var currentExpiration = claims.getExpiration();
        var newExpiration = new Date(currentExpiration.getTime() + EXPIRATION_TIME);
        log.debug("New expiration: {}", newExpiration);
        claims.setExpiration(newExpiration);

        var newToken =  Jwts.builder()
                .setClaims(claims)
                .signWith(getSignInKey(), SIGNATURE_ALGORITHM)
                .compact();
        log.debug("New token: {}", newToken);
        return newToken;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

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
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {

        return getExpirationDateFromToken(token).before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public List<UserRole> getUserRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        log.info("Claims: {}", claims);
        return objectMapper.convertValue(claims.get("roles"), new TypeReference<List<UserRole>>() {});
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
