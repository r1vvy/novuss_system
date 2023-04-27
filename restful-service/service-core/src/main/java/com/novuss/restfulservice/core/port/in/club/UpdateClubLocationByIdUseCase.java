package com.novuss.restfulservice.core.port.in.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubLocationByIdUseCase {
    Club updateClubLocationById(String clubId, String locationId);
}
