package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.DeleteLocationByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeleteLocationByIdAdapter implements DeleteLocationByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public void deleteLocationById(String id) {
        UUID uuid = UUID.fromString(id);
        locationJpaRepository.findById(uuid).ifPresentOrElse(
                locationEntity -> {
                    log.info("Location with id {} found", id);
                    locationJpaRepository.deleteById(uuid);
                },
                () -> {
                    log.info("Location with id {} not found", id);
                    throw new EntityNotFoundException("Location with id " + id + " not found");
                }
        );
    }
}
