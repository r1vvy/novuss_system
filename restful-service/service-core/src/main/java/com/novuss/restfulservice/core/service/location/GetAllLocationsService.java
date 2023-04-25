package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.GetAllLocationsUseCase;
import com.novuss.restfulservice.core.port.out.location.GetAllLocationsPort;
import com.novuss.restfulservice.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllLocationsService implements GetAllLocationsUseCase {
    private final GetAllLocationsPort getAllLocationsPort;
    @Override
    public List<Location> getAllLocations() {
        return getAllLocationsPort.getAllLocations();
    }
}
