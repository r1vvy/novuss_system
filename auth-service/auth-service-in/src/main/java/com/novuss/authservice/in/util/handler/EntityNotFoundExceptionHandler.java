package com.novuss.authservice.in.util.handler;

import com.novuss.authservice.core.exception.EntityNotFoundException;
import com.novuss.authservice.domain.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class EntityNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    private Map<String, Object> responseBody = new LinkedHashMap<>();

    @ExceptionHandler({EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var message = e.getMessage();
        var requestURI = request.getDescription(false).substring(4);

        var errorResponse = ErrorResponse.builder()
                .type(e.getClass().getSimpleName())
                .title(status.getReasonPhrase())
                .status(status.value())
                .detail(List.of(message))
                .instance(requestURI)
                .build();

        log.debug("Request failed because method argument type mismatch: {}, {}", request, message);

        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }
}
