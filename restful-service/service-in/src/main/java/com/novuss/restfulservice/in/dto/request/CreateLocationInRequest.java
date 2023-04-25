package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateLocationInRequest(
        String title,
        String city,
        String address,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
