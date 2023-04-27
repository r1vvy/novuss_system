package com.novuss.restfulservice.domain;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record CompetitionCategory(
        UUID id,
        String title,
        String tagColor
) {
}
