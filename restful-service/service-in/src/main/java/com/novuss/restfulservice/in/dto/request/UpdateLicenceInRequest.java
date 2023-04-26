package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.util.Date;

@Builder
public record UpdateLicenceInRequest(
    String title,
    Date issuedDate
) {
}
