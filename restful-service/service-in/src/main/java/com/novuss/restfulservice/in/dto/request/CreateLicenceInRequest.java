package com.novuss.restfulservice.in.dto.request;

import lombok.Builder;

import java.util.Date;

@Builder
public record CreateLicenceInRequest(
        String title,
        Date issuedDate
) {
}
