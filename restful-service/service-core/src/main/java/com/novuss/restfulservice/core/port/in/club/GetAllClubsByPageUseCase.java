package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllClubsByPageUseCase {
    Page<Club> getAllByPage(Pageable pageable);
}
