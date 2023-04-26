package com.novuss.restfulservice.repository.converter;

import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.entity.LicenceEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LicenceDomainToEntityConverter {

    public static LicenceEntity convert(Licence licence) {
        return LicenceEntity.builder()
                .id(licence.id())
                .title(licence.title())
                .issuedDate(licence.issuedDate())
                .createdAt(licence.createdAt())
                .updatedAt(licence.updatedAt())
                .build();
    }
}
