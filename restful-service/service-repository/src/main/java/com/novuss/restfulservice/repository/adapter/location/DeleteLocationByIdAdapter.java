package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.DeleteLocationByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DeleteLocationByIdAdapter implements DeleteLocationByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public void deleteLocationById(String id) {
        var locationId = UUID.fromString(id);
        locationJpaRepository.findById(locationId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Location not found with id = " + id)
        );

        locationJpaRepository.deleteById(locationId);
        log.info("Location with id {} deleted", id);
    }
}
