package com.novuss.restfulservice.repository.adapter.licence;

import com.novuss.restfulservice.core.port.out.licence.SaveLicencePort;
import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.converter.LicenceDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.LicenceEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SaveLicenceAdapter implements SaveLicencePort {
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public Licence save(Licence licence) {
        var licenceEntity = LicenceDomainToEntityConverter.convert(licence);
        var savedLicenceEntity = licenceJpaRepository.save(licenceEntity);

        return LicenceEntityToDomainConverter.convert(savedLicenceEntity);
    }
}
