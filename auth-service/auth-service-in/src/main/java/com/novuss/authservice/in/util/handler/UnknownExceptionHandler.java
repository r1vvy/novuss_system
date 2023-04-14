package com.novuss.authservice.in.util.handler;

import com.novuss.authservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Order
@Slf4j
public class UnknownExceptionHandler extends ResponseEntityExceptionHandler {

    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleUnknownException(Exception e, WebRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var message = e.getMessage();
        var requestURI = request.getDescription(false).substring(4);

        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(List.of(message))
                .instance(requestURI)
                .build();

        log.debug("Request failed because method argument type mismatch: {}, {}", request, e.getMessage());

        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }
}
