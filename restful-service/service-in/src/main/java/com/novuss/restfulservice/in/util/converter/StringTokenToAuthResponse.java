package com.novuss.restfulservice.in.util.converter;

import com.novuss.restfulservice.in.dto.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class StringTokenToAuthResponse {

    public static AuthResponse convert(String token) {
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
