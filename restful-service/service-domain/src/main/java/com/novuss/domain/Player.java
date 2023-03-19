package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

public record Player(
        UUID id,
        String image,
        Integer rating,
        Enum<Gender> gender,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Person person,
        Club club,
        Licence licence,
        SportsClass sportsClass
) {
}
