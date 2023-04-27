package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.UpdateClubLocationByIdUseCase;
import com.novuss.restfulservice.core.port.out.club.UpdateClubLocationByIdPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateClubLocationByIdService implements UpdateClubLocationByIdUseCase {
    private final UpdateClubLocationByIdPort updateClubLocationByIdPort;
    @Override
    public Club updateClubLocationById(String clubId, String locationId) {
        return updateClubLocationByIdPort.updateClubLocationById(clubId, locationId);
    }
}
