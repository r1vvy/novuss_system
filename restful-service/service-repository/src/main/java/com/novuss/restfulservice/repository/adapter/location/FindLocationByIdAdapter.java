package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.FindLocationByIdPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindLocationByIdAdapter implements FindLocationByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Location getLocationById(String id) {
        return locationJpaRepository.findById(UUID.fromString(id))
                .map(LocationEntityToDomainConverter::convert)
                .orElseThrow(() ->
                        new EntityNotFoundException("Location not found with id = " + id)
                );
    }
}
