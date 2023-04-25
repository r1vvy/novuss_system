package com.novuss.restfulservice.core.service.location;

import com.novuss.restfulservice.core.port.in.location.SaveLocationUseCase;
import com.novuss.restfulservice.core.port.out.location.SaveLocationPort;
import com.novuss.restfulservice.domain.Location;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveLocationSevice implements SaveLocationUseCase {
    private final SaveLocationPort saveLocationPort;
    @Override
    public Location save(Location location) {
        return saveLocationPort.saveLocation(location);
    }
}
