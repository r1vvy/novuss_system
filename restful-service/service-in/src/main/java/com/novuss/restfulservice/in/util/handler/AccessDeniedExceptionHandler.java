package com.novuss.restfulservice.in.util.handler;

import com.novuss.restfulservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class AccessDeniedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        var status = HttpStatus.FORBIDDEN;
        var message = "Authorization failed: " + e.getMessage();
        var requestURI = request.getDescription(false).substring(4);

        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(List.of(message))
                .instance(requestURI)
                .build();

        log.debug("Request failed due to Access Denied Exception: {}, {}", request, e.getMessage());

        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }
}
