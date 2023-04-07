package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;

import java.util.Optional;

public interface FindRefereeByIdPort {
    Optional<Referee> findById(String id);
}
