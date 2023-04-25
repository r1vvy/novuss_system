package com.novuss.restfulservice.core.port.out.location;

import com.novuss.restfulservice.domain.Location;

import java.util.List;

public interface GetAllLocationsPort {
    List<Location> getAllLocations();
}
