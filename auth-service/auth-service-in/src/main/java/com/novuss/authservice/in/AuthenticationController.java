package com.novuss.authservice.in;

import com.novuss.authservice.core.port.in.token.AuthenticateUserByUsernameUseCase;
import com.novuss.authservice.in.converter.StringToAuthenticationResponseConverter;
import com.novuss.authservice.in.dto.request.AuthenticationRequest;
import com.novuss.authservice.in.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticateUserByUsernameUseCase AuthenticateUserByUsernameUseCase;
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var token = AuthenticateUserByUsernameUseCase.authenticateUserByUsername(request.username(), request.password());
        var response = StringToAuthenticationResponseConverter.convert(token);

        return ResponseEntity.ok(response);
    }
}
