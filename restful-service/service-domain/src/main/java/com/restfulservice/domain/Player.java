package com.restfulservice.domain;

import java.time.ZonedDateTime;
import java.util.UUID;

public record Player(
        UUID id,
        String image,
        Integer rating,
        Enum<Gender> gender,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt,
        Person person,
        Club club,
        Licence licence,
        SportsClass sportsClass
) {
}
