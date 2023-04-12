package com.novuss.restfulservice.in.util;

import com.novuss.restfulservice.core.port.in.AuthorizeUserUseCase;
import com.novuss.restfulservice.core.port.in.GetUserIdFromTokenUseCase;
import com.novuss.restfulservice.core.port.in.GetUserAuthoritiesByUserIdUseCase;
import com.novuss.restfulservice.core.port.in.TokenValidationUseCase;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.converter.StringToAuthResponse;
import com.novuss.restfulservice.in.dto.response.AuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthorizeUserUseCase authorizeUserUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        var token = authorizationHeader.substring(7);
        var endpoint = request.getRequestURI();
        var method = HttpMethod.valueOf(request.getMethod());

        var requiredAuthorities = getRequiredAuthoritiesForEndpoint(handler, endpoint, method);
        if (requiredAuthorities.isEmpty()) {
            return true;
        }
        var authResponse = StringToAuthResponse.convert(
                authorizeUserUseCase.authorize(token, requiredAuthorities)
        );

        if (authResponse == null || authResponse.token() == null) {
            throw new RuntimeException("Invalid authorization response");
        }

        // Add the new token to the response headers
        response.setHeader("Authorization", "Bearer " + authResponse.token());

        return true;
    }

    private List<UserRole> getRequiredAuthoritiesForEndpoint(Object handler, String endpoint, HttpMethod method) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresAuthority requiresAuthority = handlerMethod.getMethodAnnotation(RequiresAuthority.class);
            if (requiresAuthority != null) {
                return Arrays.asList(requiresAuthority.value());
            }
        }
        return Collections.emptyList();
    }
}
