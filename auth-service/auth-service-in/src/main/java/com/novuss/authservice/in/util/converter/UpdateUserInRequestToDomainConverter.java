package com.novuss.authservice.in.util.converter;

import com.novuss.authservice.domain.User;
import com.novuss.authservice.in.dto.request.UpdateUserInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserInRequestToDomainConverter {
    public static User convert(UpdateUserInRequest request) {
        return User.builder()
                .username(request.username())
                .email(request.email())
                .password(request.password())
                .build();
    }
}
