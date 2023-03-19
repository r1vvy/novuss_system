package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

public record CompetitionReferee(
        Competition competition,
        Referee referee,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
