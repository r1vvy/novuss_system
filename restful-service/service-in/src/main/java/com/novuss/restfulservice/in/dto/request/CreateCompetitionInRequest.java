package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

import java.util.Date;

@Builder(toBuilder = true)
public record CreateCompetitionInRequest(
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Title should only contain letters, numbers, and spaces")
        String title,
        Date registrationStart,
        Date registrationEnd,
        Date competitionStart,
        Date competitionEnd,
        String locationId,
        String categoryId
) {
}
