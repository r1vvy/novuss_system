package com.novuss.restfulservice.core.port.out.referee;

import com.novuss.restfulservice.domain.Referee;

public interface UpdateRefereeCategoryForRefereePort {
    Referee updateRefereeCategoryForReferee(String refereeId, String refereeCategoryId);
}
