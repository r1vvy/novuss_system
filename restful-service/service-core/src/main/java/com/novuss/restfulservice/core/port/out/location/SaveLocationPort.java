package com.novuss.restfulservice.core.port.out.location;

import com.novuss.restfulservice.domain.Location;

public interface SaveLocationPort {

    Location saveLocation(Location location);
}
