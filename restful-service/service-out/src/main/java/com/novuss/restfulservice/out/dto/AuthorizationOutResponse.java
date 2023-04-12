package com.novuss.restfulservice.out.dto;

import lombok.Builder;

@Builder
public record AuthorizationOutResponse(
    String token
) {
}
