package com.novuss.restfulservice.repository.adapter.location;

import com.novuss.restfulservice.core.port.out.location.GetAllLocationsPort;
import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.converter.LocationEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllLocationsAdapter implements GetAllLocationsPort {
    private final LocationJpaRepository locationJpaRepository;
    @Override
    public List<Location> getAllLocations() {
        return locationJpaRepository.findAll()
                .stream()
                .map(LocationEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
