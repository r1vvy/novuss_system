package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;

public interface UpdateRefereeByIdPort {
    Referee updateById(String id, Referee referee);
}
