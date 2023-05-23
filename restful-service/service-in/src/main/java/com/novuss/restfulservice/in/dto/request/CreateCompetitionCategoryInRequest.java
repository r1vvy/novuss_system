package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

@Builder(toBuilder = true)
public record CreateCompetitionCategoryInRequest(
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Title should only contain letters, numbers, and spaces")
        String title,
        @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Tag color should be a valid hex color code")
        String tagColor
) {
}


