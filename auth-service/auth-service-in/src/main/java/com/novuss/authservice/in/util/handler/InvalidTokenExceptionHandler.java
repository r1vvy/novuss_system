package com.novuss.authservice.in.util.handler;

import com.novuss.authservice.security.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Component
@Slf4j
public class InvalidTokenExceptionHandler extends ResponseEntityExceptionHandler {
    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler(value = InvalidTokenException.class)
    protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException e, WebRequest request) {
        log.debug("Request failed due to Invalid Token Exception: {}, {}", request, e.getMessage());
        responseBody.put("timestamp", LocalDateTime.now(ZoneOffset.UTC));
        responseBody.put("status", HttpStatus.UNAUTHORIZED);

        String message = "Invalid token: " + e.getMessage();
        responseBody.put("message", message);

        return handleExceptionInternal(e, responseBody, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }
}
