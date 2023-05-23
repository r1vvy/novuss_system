package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;
import lombok.NonNull;

@Builder(toBuilder = true)
public record CreateCompetitionRefereeInRequest(
        @NonNull
        String role
) {
}
