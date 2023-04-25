package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;

import java.util.List;

public interface GetAllClubsUseCase {
    List<Club> getAll();
}
