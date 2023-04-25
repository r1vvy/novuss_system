package com.novuss.restfulservice.core.port.in.location;

import com.novuss.restfulservice.domain.Location;

public interface SaveLocationUseCase {
    Location save(Location location);
}
