package com.novuss.restfulservice.core.port.out.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubByIdPort {
    Club updateById(Club club, String id);
}
