package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubByIdUseCase {
    Club updateById(Club club, String id);
}
