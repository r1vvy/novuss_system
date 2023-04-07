package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;

public interface GetRefereeByIdUseCase {
    Referee getById(String id);
}
