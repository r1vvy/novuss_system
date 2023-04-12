package com.novuss.restfulservice.in.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
    String token
) {
}
