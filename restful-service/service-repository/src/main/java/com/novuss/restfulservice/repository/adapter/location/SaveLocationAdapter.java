package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.port.out.location.SaveLocationPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveLocationAdapter implements SaveLocationPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public Location saveLocation(Location location) {
        locationJpaRepository.findByTitle(location.title())
                .ifPresent(
                        (existingLocation) -> {
                            throw new EntityExistsException("Location already exists with title = " + location.title());
                        }
                );
        var locationEntity = LocationDomainToEntityConverter.convert(location);
        var updatedEntity = locationJpaRepository.save(locationEntity);

        return LocationEntityToDomainConverter.convert(updatedEntity);
    }
}
