package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

@Builder
public record CreateSportsClassInRequest(
        String title
) {
}
