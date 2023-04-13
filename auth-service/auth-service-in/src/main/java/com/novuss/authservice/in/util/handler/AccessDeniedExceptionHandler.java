package com.novuss.authservice.in.util.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class AccessDeniedExceptionHandler extends ResponseEntityExceptionHandler {
    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAuthenticationException(AccessDeniedException e, WebRequest request) {
        responseBody.put("timestamp", LocalDateTime.now(ZoneOffset.UTC));
        responseBody.put("status", HttpStatus.FORBIDDEN);

        String message = "Authentication failed: " + e.getMessage();
        responseBody.put("message", message);

        log.debug("Request failed due to Authentication Exception: {}, {}", request, e.getMessage());

        return new ResponseEntity<>(responseBody, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
