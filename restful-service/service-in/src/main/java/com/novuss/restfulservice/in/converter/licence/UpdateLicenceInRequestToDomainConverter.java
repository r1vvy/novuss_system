package com.novuss.restfulservice.in.converter.licence;

import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.in.dto.request.UpdateLicenceInRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateLicenceInRequestToDomainConverter {

    public static Licence convert(UpdateLicenceInRequest request) {
        return Licence.builder()
                .title(request.title())
                .issuedDate(request.issuedDate())
                .build();
    }
}
