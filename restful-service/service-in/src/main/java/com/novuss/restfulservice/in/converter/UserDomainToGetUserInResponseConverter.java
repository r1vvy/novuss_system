package com.novuss.restfulservice.in.converter;

import com.novuss.restfulservice.in.dto.GetUserInResponse;

public class UserDomainToGetUserInResponseConverter {

    public static GetUserInResponse convert (User user) {
        return GetUserInResponse.builder()
                .id(user.id())
                .username(user.username())
                .email(user.email())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .build();
    }
}
