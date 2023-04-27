package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubContactPersonByIdUseCase {
    Club updateClubContactPersonById(String clubId, String personId);
}
