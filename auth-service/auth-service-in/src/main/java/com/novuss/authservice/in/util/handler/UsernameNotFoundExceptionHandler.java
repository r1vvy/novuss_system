package com.novuss.authservice.in.util.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class UsernameNotFoundExceptionHandler extends ResponseEntityExceptionHandler {
    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({org.springframework.security.core.userdetails.UsernameNotFoundException.class})
    protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e, WebRequest request) {
        responseBody.put("timestamp", LocalDateTime.now(ZoneOffset.UTC));
        responseBody.put("status", HttpStatus.BAD_REQUEST);

        String message = "Authentication failed: " + e.getMessage();
        responseBody.put("message", message);

        log.debug("Request failed due to UsernameNotFound exception: {}, {}", request, e.getMessage());

        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
