package com.novuss.authservice.in.util.handler;

import com.novuss.authservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MethodArgumentNotValidExceptionHandler {
    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentNotValidException e, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var message = e.getFieldErrors()
                 .stream()
                 .map(FieldError::getDefaultMessage)
                 .collect(Collectors.toList());
        var requestURI = request.getDescription(false).substring(4);


        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(message)
                .instance(requestURI)
                .build();

        log.debug("Request failed because method argument type mismatch: {}, {}", request, e.getMessage());

        return new ResponseEntity<>(errorResponse, status);
    }
}
