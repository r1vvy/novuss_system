package com.novuss.restfulservice.core.port.out.location;

import com.novuss.restfulservice.domain.Location;

public interface UpdateLocationContactPersonByIdPort {
    Location update(String locationId, String contactPersonId);
}
