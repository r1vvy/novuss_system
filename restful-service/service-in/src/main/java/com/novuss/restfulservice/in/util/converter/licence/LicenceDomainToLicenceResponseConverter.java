package com.novuss.restfulservice.in.util.converter.licence;

import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.in.dto.response.LicenceResponse;
import org.springframework.stereotype.Component;

@Component
public class LicenceDomainToLicenceResponseConverter {

    public static LicenceResponse convert(Licence licence) {
        return LicenceResponse.builder()
                .id(licence.id().toString())
                .title(licence.title())
                .issuedDate(licence.issuedDate())
                .createdAt(licence.createdAt())
                .updatedAt(licence.updatedAt())
                .build();
    }
}
