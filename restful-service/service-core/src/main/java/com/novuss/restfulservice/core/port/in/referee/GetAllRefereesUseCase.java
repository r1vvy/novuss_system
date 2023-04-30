package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllRefereesUseCase {
    Page<Referee> getAllByPage(Pageable pageable);
}
