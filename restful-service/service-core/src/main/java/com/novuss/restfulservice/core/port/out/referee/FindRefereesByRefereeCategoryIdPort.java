package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;

import java.util.List;

public interface FindRefereesByRefereeCategoryIdPort {
    List<Referee> findByRefereeCategoryId(String refereeCategoryId);
}
