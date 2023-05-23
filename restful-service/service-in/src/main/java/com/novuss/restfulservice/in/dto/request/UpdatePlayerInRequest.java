package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;


@Builder
public record UpdatePlayerInRequest(
        Integer rating,
        String gender
) {
}
