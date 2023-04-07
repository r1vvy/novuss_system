package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;

import java.util.List;

public interface GetAllRefereesUseCase {
    List<Referee> getAll();
}
