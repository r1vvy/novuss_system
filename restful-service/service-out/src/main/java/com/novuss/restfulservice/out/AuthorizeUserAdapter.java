package com.novuss.restfulservice.out;

import com.novuss.restfulservice.core.port.out.AuthorizeUserPort;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.out.dto.AuthorizationOutRequest;
import com.novuss.restfulservice.out.dto.AuthorizationOutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserAdapter implements AuthorizeUserPort {
    private final RestTemplate restTemplate;
    private static final String AUTH_URL = "http://localhost:8000/api/v1/auth/authorize";

    @Override
    public String authorize(String token, List<UserRole> requiredAuthorities) {
        var request = AuthorizationOutRequest.builder()
                .token(token)
                .requiredAuthorities(requiredAuthorities)
                .build();
        try {
            ResponseEntity<AuthorizationOutResponse> response = restTemplate.postForEntity(AUTH_URL, request, AuthorizationOutResponse.class);

            if(response.getStatusCode() != HttpStatus.OK) {
                log.warn("Authorization failed");
                throw new RuntimeException("Authorization failed");
            }

            return response.getBody().token();
        } catch (Exception e) {
            log.error("Error authorizing user", e);
            throw new RuntimeException("Error authorizing user");
        }
    }
}
