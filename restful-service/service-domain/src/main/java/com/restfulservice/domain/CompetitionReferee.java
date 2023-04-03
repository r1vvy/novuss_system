package com.restfulservice.domain;

import java.time.ZonedDateTime;

public record CompetitionReferee(
        Competition competition,
        Referee referee,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
}
