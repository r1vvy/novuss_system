package com.novuss.restfulservice.out.adapter;

import com.novuss.restfulservice.core.exception.OutgoingAuthorizationServiceException;
import com.novuss.restfulservice.core.port.out.AuthorizeUserPort;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.out.dto.AuthorizationOutRequest;
import com.novuss.restfulservice.out.dto.AuthorizationOutResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizeUserAdapter implements AuthorizeUserPort {
    private final RestTemplate restTemplate;
    private static final String AUTH_URL = "http://localhost:8000/api/v1/auth/authorize";

    @Override
    public String authorize(String token, List<UserRole> requiredAuthorities) {
        log.info("Authorizing user");
        var request = AuthorizationOutRequest.builder()
                .token(token)
                .requiredAuthorities(requiredAuthorities)
                .build();

        log.debug("Request: {}", request);
        try {
            var response = restTemplate.postForEntity(AUTH_URL, request, AuthorizationOutResponse.class);
            log.debug("Response: {}, {}", response.getStatusCode(), response.getBody());

            return Objects.requireNonNull(response.getBody())
                    .token();
        } catch (RestClientException | NullPointerException restClientException) {
            log.error("Error authorizing user: {}", restClientException.getMessage());
            throw new OutgoingAuthorizationServiceException("Failed to authorize user in auth service");
        }
    }
}
