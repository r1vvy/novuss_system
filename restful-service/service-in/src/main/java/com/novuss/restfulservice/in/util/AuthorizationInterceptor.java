package com.novuss.restfulservice.in.util;

import com.novuss.restfulservice.core.port.in.GetUserIdFromTokenUseCase;
import com.novuss.restfulservice.core.port.in.GetUserAuthoritiesByUserIdUseCase;
import com.novuss.restfulservice.core.port.in.TokenValidationUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final TokenValidationUseCase tokenValidationUseCase;
    private final GetUserAuthoritiesByUserIdUseCase getUserAuthoritiesByUserIdUseCase;
    private final GetUserIdFromTokenUseCase getUserIdFromTokenUseCase;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var requiresAuthority = getRequiredAuthorityAnnotation(handler);

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return false;
        }

        var token = authorizationHeader.substring(7);
        if (!tokenValidationUseCase.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return false;
        }

        if(requiresAuthority != null) {
            var userId = getUserIdFromTokenUseCase.getUserIdFromToken(token);
            var userAuthorities = getUserAuthoritiesByUserIdUseCase.getUserAuthorityByUserId(userId);
            var requiredAuthorities = Arrays.asList(requiresAuthority.value());
            if (!userAuthorities.containsAll(requiredAuthorities)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "User has insufficient privileges");
                return false;
            }
        }

        return true;
    }

    private RequiresAuthority getRequiredAuthorityAnnotation(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresAuthority requiresAuthority = handlerMethod.getMethodAnnotation(RequiresAuthority.class);
            if (requiresAuthority != null) {
                return requiresAuthority;
            }
            return handlerMethod.getBeanType().getAnnotation(RequiresAuthority.class);
        }
        return null;
    }
}
