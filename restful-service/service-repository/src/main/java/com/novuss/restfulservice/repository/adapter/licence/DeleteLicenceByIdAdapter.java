package com.novuss.restfulservice.repository.adapter.licence;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.licence.DeleteLicenceByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.LicenceJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteLicenceByIdAdapter implements DeleteLicenceByIdPort {
    private final LicenceJpaRepository licenceJpaRepository;
    @Override
    public void deleteLicenceById(String id) {
        UUID uuid = UUID.fromString(id);

        licenceJpaRepository.findById(uuid).ifPresentOrElse(
                licenceEntity -> {
                    log.info("Licence with id {} found", id);
                    licenceJpaRepository.deleteById(uuid);
                },
                () -> {
                    log.info("Licence with id {} not found", id);
                    throw new EntityNotFoundException("Licence with id " + id + " not found");
                });
    }
}
