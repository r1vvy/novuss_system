package com.novuss.authservice.in;

import com.novuss.authservice.core.port.in.token.AuthorizeRequestByTokenUseCase;
import com.novuss.authservice.core.port.in.token.ExtendTokenExpiryUseCase;
import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.in.converter.TokenInStringToAuthenticationResponseConverter;
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
        var response = TokenInStringToAuthenticationResponseConverter.convert(token);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/authorize")
    public ResponseEntity<AuthResponse> authorize(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AuthorizationRequest request) {
        log.info("Recieved authorization request");

        var token = authorizationHeader.replace("Bearer ", "");
        authorizeRequestByTokenUseCase.authorize(token, request.requiredAuthorities());
        var newToken = extendTokenExpiryUseCase.extendTokenExpiry(token);
        log.info("Extended token expiry");

        var response = TokenInStringToAuthenticationResponseConverter.convert(newToken);
        log.info("Converted token to response: {}", response);

        return ResponseEntity.ok(response);
    }
}
