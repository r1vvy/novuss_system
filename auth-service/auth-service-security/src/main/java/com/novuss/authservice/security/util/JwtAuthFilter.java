package com.novuss.authservice.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.novuss.authservice.domain.ErrorResponse;
import com.novuss.authservice.security.exception.InvalidTokenException;
import com.novuss.authservice.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException {
        log.info("Validating request for URI: " + request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            doFilter(filterChain, request, response);

            log.warn("Missing or invalid Authorization header");
            return;
        }

        jwt = authHeader.replace("Bearer ", "");
        log.debug("JWT token: {}", jwt);

        try {
            username = jwtService.getUsernameFromToken(jwt);
        } catch (InvalidTokenException e) {
            log.warn("Invalid token: " + e.getMessage());
            responseWithError(response, HttpStatus.UNAUTHORIZED, e);

            return;
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                log.info("Checking authentication for user " + username);
                var userDetails = this.userDetailsService.loadUserByUsername(username);

                if(jwtService.isTokenValid(jwt, userDetails)) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("User " + username + " authenticated successfully");
                } else {
                    log.warn("Invalid token for user " + username);
                    responseWithError(response, HttpStatus.UNAUTHORIZED, new InvalidTokenException("Invalid token"));
                }

                doFilter(filterChain, request, response);
            } catch (UsernameNotFoundException e) {
                log.warn("User " + username + " not found");
                responseWithError(response, HttpStatus.NOT_FOUND, e);
            } catch (InvalidTokenException e) {
                log.warn("Invalid token: " + e.getMessage());
                responseWithError(response, HttpStatus.UNAUTHORIZED, e);
            }
        }
    }

    private void doFilter(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try{
            filterChain.doFilter(request, response);
        } catch(IOException e) {
            log.error("IOException: " + e.getMessage());
            responseWithError(response, HttpStatus.INTERNAL_SERVER_ERROR ,e);
        } catch(ServletException e) {
            log.error("ServletException: " + e.getMessage());
            responseWithError(response, HttpStatus.INTERNAL_SERVER_ERROR ,e);
        }
    }

    private static HttpServletResponse responseWithError(HttpServletResponse response,
                                                         HttpStatus status,
                                                         Exception e) throws IOException{
        var type = e.getClass().getSimpleName();
        var title = status.getReasonPhrase();
        var detail = e.getMessage();

        var errorResponse = ErrorResponse.builder()
                .type(type)
                .title(title)
                .status(status.value())
                .detail(List.of(detail))
                .build();

        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .build();

        String json = mapper.writeValueAsString(errorResponse);

        response.setContentType("application/json");
        response.setStatus(status.value());
        response.getWriter().write(json);

        return response;
    }
}
