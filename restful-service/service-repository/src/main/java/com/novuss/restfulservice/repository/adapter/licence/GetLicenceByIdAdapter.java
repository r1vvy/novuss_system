package com.novuss.restfulservice.repository.adapter.licence;

import com.novuss.restfulservice.core.port.out.licence.GetLicenceByIdPort;
import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.converter.LicenceEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetLicenceByIdAdapter implements GetLicenceByIdPort {
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public Optional<Licence> getById(String id) {
        var licenceEntity = licenceJpaRepository.findById(UUID.fromString(id));

        return licenceEntity.map(LicenceEntityToDomainConverter::convert);
    }
}
