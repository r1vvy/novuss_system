package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.UpdateLocationByIdUseCase;
import com.novuss.restfulservice.core.port.out.location.UpdateLocationByIdPort;
import com.novuss.restfulservice.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateLocationByIdService implements UpdateLocationByIdUseCase {
    private final UpdateLocationByIdPort updateLocationByIdPort;
    @Override
    public Location updateLocationById(Location location, String id) {
        return updateLocationByIdPort.updateLocationById(location, id);
    }
}
