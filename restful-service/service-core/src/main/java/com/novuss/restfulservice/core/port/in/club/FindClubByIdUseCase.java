package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;

public interface FindClubByIdUseCase {
    Club findById(String id);
}
