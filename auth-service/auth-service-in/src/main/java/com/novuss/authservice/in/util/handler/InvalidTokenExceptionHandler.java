package com.novuss.authservice.in.util.handler;

import com.novuss.authservice.domain.ErrorResponse;
import com.novuss.authservice.security.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class InvalidTokenExceptionHandler extends ResponseEntityExceptionHandler {
    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({InvalidTokenException.class})
    protected ResponseEntity<Object> handleInvalidTokenException(InvalidTokenException e, WebRequest request) {
        var status = HttpStatus.UNAUTHORIZED;
        var message = "Invalid token: " + e.getMessage();
        var requestURI = request.getDescription(false).substring(4);
        //responseBody.put("timestamp", LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(List.of(message))
                .instance(requestURI)
                .build();

        log.debug("Request failed due to Invalid Token Exception: {}, {}", request, e.getMessage());

        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }
}
