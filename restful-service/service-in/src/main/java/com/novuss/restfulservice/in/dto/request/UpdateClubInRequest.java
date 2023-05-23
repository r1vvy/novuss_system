package com.novuss.restfulservice.in.dto.request;

import com.novuss.restfulservice.domain.Location;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record UpdateClubInRequest(
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Title should only contain letters, numbers, and spaces")
        String title,
        Location location
) {
}
