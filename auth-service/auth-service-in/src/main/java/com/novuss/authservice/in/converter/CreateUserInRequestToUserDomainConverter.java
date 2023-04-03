package com.novuss.authservice.in.converter;

import com.novuss.authservice.domain.User;
import com.novuss.authservice.in.dto.request.CreateUserInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateUserInRequestToUserDomainConverter {
    public static User convert (CreateUserInRequest createUserInRequest) {
        return User.builder()
                .username(createUserInRequest.username())
                .email(createUserInRequest.email())
                .roles(createUserInRequest.roles())
                .password(createUserInRequest.password())
                .build();
    }
}
