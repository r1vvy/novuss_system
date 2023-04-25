package com.novuss.restfulservice.repository.adapter.licence;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.licence.UpdateLicenceByIdPort;
import com.novuss.restfulservice.domain.Licence;
import com.novuss.restfulservice.repository.converter.LicenceDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.LicenceEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateLicenceByIdAdapter implements UpdateLicenceByIdPort {
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public Licence updateLicenceById(Licence licence, String id) {
        var uuid = UUID.fromString(id);
        var oldEntity = licenceJpaRepository.findById(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("Licence not found with id = " + id)
                );

        var updatedEntity = LicenceDomainToEntityConverter.convert(licence);
        updatedEntity.setId(uuid);
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedLicence = licenceJpaRepository.save(updatedEntity);

        return LicenceEntityToDomainConverter.convert(updatedLicence);
    }
}
