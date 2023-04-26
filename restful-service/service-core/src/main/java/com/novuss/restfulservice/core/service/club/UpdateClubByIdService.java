package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.UpdateClubByIdUseCase;
import com.novuss.restfulservice.core.port.out.club.UpdateClubByIdPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateClubByIdService implements UpdateClubByIdUseCase {
    private final UpdateClubByIdPort updateClubByIdPort;
    @Override
    public Club updateById(Club club, String id) {
        return updateClubByIdPort.updateById(club, id);
    }
}
