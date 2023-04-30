package com.novuss.restfulservice.core.port.out.club;

import com.novuss.restfulservice.domain.Club;

public interface FindClubByIdPort {
    Club findById(String id);
}
