package com.novuss.restfulservice.in.dto.request;

public record UpdateCompetitionPlayerInRequest(
        String playerId,
        Integer placement,
        Integer ratingChange
) {
}
