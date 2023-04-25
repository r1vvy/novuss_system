package com.novuss.restfulservice.core.port.out.location;

import com.novuss.restfulservice.domain.Location;

public interface UpdateLocationByIdPort {
    Location updateLocationById(Location location, String id);
}
