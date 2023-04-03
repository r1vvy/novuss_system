package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.request.CreateUserInRequest;
import com.restfulservice.domain.User;

public class CreateUserInRequestToUserDomainConverter {
    public static User convert (CreateUserInRequest createUserInRequest) {
        return User.builder()
                .username(createUserInRequest.username())
                .email(createUserInRequest.email())
                .password(createUserInRequest.password())
                .build();
    }
}
