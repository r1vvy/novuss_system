package com.novuss.restfulservice.in.util.handler;

import com.novuss.restfulservice.core.exception.AuthorizationException;
import com.novuss.restfulservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class AuthorizationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AuthorizationException.class})
    protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException e, WebRequest request) {
        var status = HttpStatus.UNAUTHORIZED;
        var message = "Authentication failed: " + e.getMessage();
        var requestURI = request.getDescription(false).substring(4);

        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(List.of(message))
                .instance(requestURI)
                .build();

        log.debug("Request failed due to Bad Credentials Exception: {}, {}", request, e.getMessage());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), status);
    }
}
