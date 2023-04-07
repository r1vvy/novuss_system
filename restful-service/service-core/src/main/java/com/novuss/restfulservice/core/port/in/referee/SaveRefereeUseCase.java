package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;

public interface SaveRefereeUseCase {
    Referee save(Referee referee);
}
