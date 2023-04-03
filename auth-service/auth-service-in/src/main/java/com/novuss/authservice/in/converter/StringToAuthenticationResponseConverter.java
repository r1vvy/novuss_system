package com.novuss.authservice.in.converter;

import com.novuss.authservice.in.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class StringToAuthenticationResponseConverter {

    public static AuthenticationResponse convert (String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
