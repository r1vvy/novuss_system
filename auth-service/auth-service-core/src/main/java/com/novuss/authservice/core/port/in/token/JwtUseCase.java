package com.novuss.authservice.core.port.in.token;

import com.novuss.authservice.domain.UserRole;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public interface JwtUseCase {
    String extendExpirationTime(String token);
    String getUsernameFromToken(String token);
    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    Claims getAllClaimsFromToken(String token);
    List<UserRole> getUserRolesFromToken(String token);
}
