package com.novuss.restfulservice.out.adapter;

import com.novuss.restfulservice.core.exception.AccessDeniedException;
import com.novuss.restfulservice.core.exception.AuthorizationException;
import com.novuss.restfulservice.core.exception.OutgoingAuthorizationServiceException;
import com.novuss.restfulservice.core.port.out.AuthorizeUserPort;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.out.dto.AuthorizationOutRequest;
import com.novuss.restfulservice.out.dto.AuthorizationOutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserAdapter implements AuthorizeUserPort {
    private final RestTemplate restTemplate;

    // TODO: move this to config
    private static final String AUTH_URL = "http://localhost:8000/api/v1/auth/authorize";

    @Override
    public void authorize(String token, List<UserRole> requiredAuthorities) {
        log.info("Authorizing user");
        var request = AuthorizationOutRequest.builder()
                .requiredAuthorities(requiredAuthorities)
                .build();
        log.debug("Request: {}", request);

        try {
            var response = restTemplate.postForEntity(AUTH_URL, request, AuthorizationOutResponse.class);
        } catch (HttpClientErrorException e) {
            log.error("Error authorizing user: {}", e.getMessage());
            handleHttpClientErrorException(e);
        } catch (RestClientException | NullPointerException e) {
            log.error("Error authorizing user: {}", e.getMessage());
            throw new OutgoingAuthorizationServiceException("Failed to authorize user while using auth service: " + e.getMessage());
        }
    }

    private void handleHttpClientErrorException(HttpClientErrorException e) {
        var statusCode = e.getStatusCode();
        log.error("Error authorizing user: {}", e.getMessage());
        if (statusCode.isSameCodeAs(FORBIDDEN)) {
            throw new AccessDeniedException("Failed to authorize user while using auth service: " + e.getMessage());
        } else if (statusCode.isSameCodeAs(UNAUTHORIZED)) {
            throw new AuthorizationException("Failed to authorize user while using auth service: " + e.getMessage());
        } else {
            throw new OutgoingAuthorizationServiceException("Failed to authorize user while using auth service: " + e.getMessage());
        }
    }
}
