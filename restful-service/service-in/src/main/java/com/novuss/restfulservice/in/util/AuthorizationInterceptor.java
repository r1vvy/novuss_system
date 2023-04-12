package com.novuss.restfulservice.in.util;

import com.novuss.restfulservice.core.port.in.token.AuthorizeTokenUseCase;
import com.novuss.restfulservice.domain.UserRole;
import com.novuss.restfulservice.in.converter.StringTokenToAuthResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final AuthorizeTokenUseCase authorizeTokenUseCase;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Intercepting request");
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("Missing or invalid Authorization header for request: {}, {}",
                    request.getRequestURI(),
                    request.getMethod()
            );
            throw new AuthorizationServiceException("Missing or invalid Authorization header");
        }
        log.debug("Authorization header: {}", authorizationHeader);
        var token = authorizationHeader.substring(7);
        var endpoint = request.getRequestURI();
        var method = HttpMethod.valueOf(request.getMethod());

        var requiredAuthorities = getRequiredAuthoritiesForEndpoint(handler, endpoint, method);
        if (requiredAuthorities.isEmpty()) {
            return true;
        }
        log.debug("Required authorities: {}", requiredAuthorities);

        var authResponse = StringTokenToAuthResponse.convert(
                authorizeTokenUseCase.authorize(token, requiredAuthorities)
        );

        if (authResponse == null || authResponse.token() == null) {
            log.error("Invalid authorization response: {}", authResponse);
            throw new AuthorizationServiceException("Invalid authorization response");
        }
        response.setHeader("Authorization", "Bearer " + authResponse.token());

        return true;
    }

    private List<UserRole> getRequiredAuthoritiesForEndpoint(Object handler, String endpoint, HttpMethod method) {
        if (handler instanceof HandlerMethod) {
            var handlerMethod = (HandlerMethod) handler;
            var requiresAuthority = handlerMethod.getMethodAnnotation(RequiresAuthority.class);

            if (requiresAuthority != null) {
                return Arrays.asList(requiresAuthority.value());
            }
        }
        return Collections.emptyList();
    }
}
