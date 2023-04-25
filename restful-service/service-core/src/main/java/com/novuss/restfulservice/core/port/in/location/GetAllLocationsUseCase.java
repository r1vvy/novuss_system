package com.novuss.restfulservice.core.port.in.location;

import com.novuss.restfulservice.domain.Location;

import java.util.List;

public interface GetAllLocationsUseCase {
    List<Location> getAllLocations();
}
