package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Builder(toBuilder = true)
public record UpdateCompetitionRefereeInRequest(
        @NonNull
        String role
) {
}
