package com.novuss.authservice.in.dto.request;

import com.novuss.authservice.domain.UserRole;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record AuthorizationRequest(
        @NonNull
        List<UserRole> requiredAuthorities
) {
}
