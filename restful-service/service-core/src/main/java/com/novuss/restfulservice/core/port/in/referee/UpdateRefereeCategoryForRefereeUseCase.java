package com.novuss.restfulservice.core.port.in.referee;

import com.novuss.restfulservice.domain.Referee;

public interface UpdateRefereeCategoryForRefereeUseCase {
    Referee updateRefereeCategoryForReferee(String refereeId, String refereeCategoryId);
}
