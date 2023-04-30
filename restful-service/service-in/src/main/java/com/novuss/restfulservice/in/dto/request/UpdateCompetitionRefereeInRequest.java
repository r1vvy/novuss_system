package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder(toBuilder = true)
public record UpdateCompetitionRefereeInRequest(
        String role
) {
}
