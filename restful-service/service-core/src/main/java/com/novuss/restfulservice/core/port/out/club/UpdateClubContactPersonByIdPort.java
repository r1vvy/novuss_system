package com.novuss.restfulservice.core.port.out.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubContactPersonByIdPort {
    Club updateClubContactPersonById(String clubId, String personId);
}
