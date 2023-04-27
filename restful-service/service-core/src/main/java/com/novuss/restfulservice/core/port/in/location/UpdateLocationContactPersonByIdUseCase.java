package com.novuss.restfulservice.core.port.in.location;

import com.novuss.restfulservice.domain.Location;

public interface UpdateLocationContactPersonByIdUseCase {
    Location updateLocationContactPersonById(String locationId, String contactPersonId);
}
