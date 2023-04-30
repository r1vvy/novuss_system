package com.novuss.restfulservice.core.port.out.club;

import com.novuss.restfulservice.domain.Club;

public interface UpdateClubLocationByIdPort {
    Club updateClubLocationById(String clubId, String locationId);
}
