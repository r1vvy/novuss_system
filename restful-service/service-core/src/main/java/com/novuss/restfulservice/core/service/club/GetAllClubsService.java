package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.GetAllClubsUseCase;
import com.novuss.restfulservice.core.port.out.club.GetAllClubsPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllClubsService implements GetAllClubsUseCase {
    private final GetAllClubsPort getAllClubsPort;
    @Override
    public List<Club> getAll() {
        return getAllClubsPort.getAllClubs();
    }
}
