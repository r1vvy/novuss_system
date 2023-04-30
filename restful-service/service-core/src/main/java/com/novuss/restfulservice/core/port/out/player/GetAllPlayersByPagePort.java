package com.novuss.restfulservice.core.port.out.player;

import com.novuss.restfulservice.domain.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllPlayersByPagePort {
    Page<Player> getAllPlayersByPage(Pageable pageable);
}
