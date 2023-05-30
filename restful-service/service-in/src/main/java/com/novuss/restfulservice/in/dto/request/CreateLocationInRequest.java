package com.novuss.restfulservice.in.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;

@Builder
public record CreateLocationInRequest(
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Title should only contain letters, numbers, and spaces")
        String title,
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ,.-]+$", message = "City should only contain letters, numbers, spaces, commas and periods")
        String city,
        @NonNull
        @Pattern(regexp = "^[\\p{L}0-9 ,.-]+$", message = "Address should only contain letters, numbers, spaces, commas and periods")
        String address,
        @NotNull(message = "Latitude must not be null")
        @DecimalMin(value = "-90", message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90", message = "Latitude must be between -90 and 90")
        BigDecimal latitude,
        @NotNull(message = "Longitude must not be null")
        @DecimalMin(value = "-180", message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180", message = "Longitude must be between -180 and 180")
        BigDecimal longitude,
        @Valid
        CreatePersonInRequest contactPerson
) {
}
