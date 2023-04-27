package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.location.UpdateLocationByIdPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateLocationByIdAdapter implements UpdateLocationByIdPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Location updateLocationById(Location location, String id) {
        var uuid = UUID.fromString(id);

        var oldEntity = locationJpaRepository.findById(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("Location not found with id = " + id)
                );
        var updatedEntity = LocationDomainToEntityConverter.convert(location);
        updatedEntity.setPersonEntity(oldEntity.getPersonEntity());
        updatedEntity.setId(uuid);
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedLocation = locationJpaRepository.save(updatedEntity);

        return LocationEntityToDomainConverter.convert(updatedLocation);
    }
}
