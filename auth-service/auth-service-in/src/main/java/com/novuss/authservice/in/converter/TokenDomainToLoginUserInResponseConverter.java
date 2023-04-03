package com.novuss.authservice.in.converter;

import com.novuss.authservice.domain.Token;
import com.novuss.authservice.in.dto.response.AuthenticationResponse;

public class TokenDomainToLoginUserInResponseConverter {

    public static AuthenticationResponse convert(String token) {
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
