package com.novuss.authservice.in.util.converter;
import com.novuss.authservice.in.dto.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenDomainToLoginUserInResponseConverter {

    public static AuthResponse convert(String token) {
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
