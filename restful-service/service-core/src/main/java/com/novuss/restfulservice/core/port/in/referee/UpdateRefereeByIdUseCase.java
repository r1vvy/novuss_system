package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;

public interface UpdateRefereeByIdUseCase {
    Referee updateById(String id, Referee referee);
}
