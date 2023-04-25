package com.novuss.restfulservice.core.port.in.location;

import com.novuss.restfulservice.domain.Location;

public interface UpdateLocationByIdUseCase {
    Location updateLocationById(Location location, String id);
}
