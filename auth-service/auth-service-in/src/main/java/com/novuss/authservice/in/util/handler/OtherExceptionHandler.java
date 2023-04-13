package com.novuss.authservice.in.util.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class OtherExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();

        String requestURI = request.getDescription(false).substring(4);
        String message = ex.getMessage();

        responseBody.put("timestamp", LocalDateTime.now(ZoneOffset.UTC));
        responseBody.put("status", HttpStatus.BAD_REQUEST);
        responseBody.put("message", message);
        responseBody.put("requestURI", requestURI);

        log.error("Request failed because of exception: {}, {}", message, requestURI);

        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
