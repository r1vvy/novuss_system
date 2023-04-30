package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

@Builder(toBuilder = true)
public record CreateCompetitionRefereeInRequest(
        String role
) {
}
