package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.UpdateClubContactPersonByIdUseCase;
import com.novuss.restfulservice.core.port.out.club.UpdateClubContactPersonByIdPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateClubContactPersonByIdService implements UpdateClubContactPersonByIdUseCase {
    private final UpdateClubContactPersonByIdPort updateClubContactPersonByIdPort;
    @Override
    public Club updateClubContactPersonById(String clubId, String personId) {
        return updateClubContactPersonByIdPort.updateClubContactPersonById(clubId, personId);
    }
}
