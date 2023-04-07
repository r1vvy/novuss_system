package com.restfulservice.domain;

import java.util.UUID;

public record CompetitionPlayer(
        UUID id,
        Integer placement,
        Integer ratingChange,
        Competition competition,
        Player player
) {

}
