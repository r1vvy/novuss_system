package com.novuss.authservice.in;

import com.novuss.authservice.core.port.in.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.in.converter.StringToAuthenticationResponseConverter;
import com.novuss.authservice.in.dto.request.AuthenticationRequest;
import com.novuss.authservice.in.dto.request.AuthorizationRequest;
import com.novuss.authservice.in.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticateUserByUsernameUseCase AuthenticateUserByUsernameUseCase;
    private final AuthorizeRequestByTokenUseCase authorizeRequestByTokenUseCase;
    private final ExtendTokenExpiryUseCase extendTokenExpiryUseCase;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("Recieved authentication request from: {}", request.username());
        var token = AuthenticateUserByUsernameUseCase.authenticateUserByUsername(request.username(), request.password());
        var response = StringToAuthenticationResponseConverter.convert(token);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/authorize")
    public ResponseEntity<AuthResponse> authorize(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AuthorizationRequest request) {
        log.info("Recieved authorization request");
        authorizeRequestByTokenUseCase.authorize(authorizationHeader, request.requiredAuthorities());

        var newToken = extendTokenExpiryUseCase.extendTokenExpiry(authorizationHeader);
        log.info("Extended token expiry");

        var response = StringToAuthenticationResponseConverter.convert(newToken);

        return ResponseEntity.ok(response);
    }
}
