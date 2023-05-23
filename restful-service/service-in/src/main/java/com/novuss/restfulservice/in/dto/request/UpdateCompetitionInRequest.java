package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

import java.time.Instant;

@Builder(toBuilder = true)
public record UpdateCompetitionInRequest(
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Title should only contain letters, numbers, and spaces")
        String title,
        @FutureOrPresent(message = "Registration start date must be in the future or present")
        Instant registrationStart,
        @Future(message = "Registration end date must be in the future")
        Instant registrationEnd,
        @FutureOrPresent(message = "Competition start date must be in the future or present")
        Instant competitionStart,
        @Future(message = "Competition end date must be in the future")
        Instant competitionEnd
) {
}
