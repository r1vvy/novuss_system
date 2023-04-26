package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.entity.LicenceEntity;
import org.springframework.stereotype.Component;

@Component
public class LicenceEntityToDomainConverter {

    public static Licence convert(LicenceEntity licenceEntity) {
        return Licence.builder()
                .id(licenceEntity.getId())
                .title(licenceEntity.getTitle())
                .issuedDate(licenceEntity.getIssuedDate())
                .createdAt(licenceEntity.getCreatedAt())
                .updatedAt(licenceEntity.getUpdatedAt())
                .build();
    }
}
