package com.novuss.restfulservice.core.port.out.club;

import com.novuss.restfulservice.domain.Club;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllClubsByPagePort {
    Page<Club> getAll(Pageable pageable);
}
