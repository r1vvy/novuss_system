package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.club.FindClubByIdUseCase;
import com.novuss.restfulservice.core.port.out.club.FindClubByIdPort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindClubByIdService implements FindClubByIdUseCase {
    private final FindClubByIdPort findClubByIdPort;
    @Override
    public Club findById(String id) {
        log.info("Searching for club with id = {}", id);
        return findClubByIdPort.findById(id);
    }
}
