package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.SaveClubUseCase;
import com.novuss.restfulservice.core.port.out.club.SaveClubPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaveClubService implements SaveClubUseCase {
    private final SaveClubPort saveClubPort;
    @Override
    public Club save(Club club) {
        return saveClubPort.save(club);
    }
}
