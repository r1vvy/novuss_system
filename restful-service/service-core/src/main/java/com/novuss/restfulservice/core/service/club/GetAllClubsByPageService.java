package com.novuss.restfulservice.core.service.club;

import com.novuss.restfulservice.core.port.in.club.GetAllClubsByPageUseCase;
import com.novuss.restfulservice.core.port.out.club.GetAllClubsByPagePort;
import com.novuss.restfulservice.domain.Club;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllClubsByPageService implements GetAllClubsByPageUseCase {
    private final GetAllClubsByPagePort getAllClubsByPagePort;

    public Page<Club> getAllByPage(Pageable pageable) {
        log.info("Get all clubs by page = {}", pageable);

        return getAllClubsByPagePort.getAll(pageable);
    }
}
