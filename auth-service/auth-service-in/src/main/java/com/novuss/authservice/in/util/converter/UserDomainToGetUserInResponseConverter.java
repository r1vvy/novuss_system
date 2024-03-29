package com.novuss.authservice.in.util.converter;

import com.novuss.authservice.domain.User;
import com.novuss.authservice.in.dto.response.GetUserInResponse;
import org.springframework.stereotype.Component;

@Component
public class UserDomainToGetUserInResponseConverter {

    public static GetUserInResponse convert (User user) {
        return GetUserInResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
