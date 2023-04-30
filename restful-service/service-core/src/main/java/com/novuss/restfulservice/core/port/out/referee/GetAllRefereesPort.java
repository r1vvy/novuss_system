package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllRefereesPort {
    Page<Referee> getAllRefereesByPage(Pageable pageable);
}
