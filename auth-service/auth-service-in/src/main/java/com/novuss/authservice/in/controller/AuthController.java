package com.novuss.authservice.in.controller;

import com.novuss.authservice.core.port.in.token.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.user.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.in.dto.request.AuthenticationRequest;
import com.novuss.authservice.in.dto.request.AuthorizationRequest;
import com.novuss.authservice.in.dto.request.RefreshTokenRequest;
import com.novuss.authservice.in.dto.response.AuthResponse;
import com.novuss.authservice.in.util.converter.TokenInStringToAuthenticationResponseConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
@Validated
public class AuthController {
    private final AuthenticateUserByUsernameUseCase authenticateUserByUsernameUseCase;
    private final AuthorizeRequestByTokenUseCase authorizeRequestByTokenUseCase;
    private final ExtendTokenExpiryUseCase extendTokenExpiryUseCase;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        log.info("Recieved authentication request from: {}", request.username());
        var token = authenticateUserByUsernameUseCase.authenticateUserByUsername(request.username(), request.password());
        var response = TokenInStringToAuthenticationResponseConverter.convert(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/authorize")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void authorize(
            @RequestHeader("Authorization") String authorizationHeader,
            @Valid
            @RequestBody AuthorizationRequest request) {
        log.info("Recieved authorization request");

        var token = authorizationHeader.replace("Bearer ", "");
        authorizeRequestByTokenUseCase.authorizeByRequiredAuthorities(token, request.requiredAuthorities());
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @RequestBody
            RefreshTokenRequest request
    ) {
        log.info("Recieved refresh request");

        var newToken = extendTokenExpiryUseCase.extendTokenExpiry(request.token());
        log.info("Extended token expiry");

        var response = TokenInStringToAuthenticationResponseConverter.convert(newToken);
        log.info("Converted token to response: {}", response);

        return ResponseEntity.ok(response);
    }
}
