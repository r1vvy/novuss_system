package com.novuss.restfulservice.in.converter.licence;

import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.in.dto.request.CreateLicenceInRequest;
import org.springframework.stereotype.Component;

@Component
public class CreateLicenceInRequestToDomainConverter {

    public static Licence convert(CreateLicenceInRequest request) {
        return Licence.builder()
                .title(request.title())
                .issuedDate(request.issuedDate())
                .build();
    }
}
