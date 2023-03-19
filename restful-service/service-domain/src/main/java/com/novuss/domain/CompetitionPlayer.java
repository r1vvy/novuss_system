package com.novuss.domain;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

public record CompetitionPlayer(
        UUID id,
        Integer placement,
        Integer ratingChange,
        Competition competition,
        Player player
) {

}
