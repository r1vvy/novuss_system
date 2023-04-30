package com.novuss.restfulservice.core.port.in.player;

import com.novuss.restfulservice.domain.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllPlayersByPageUseCase {
    Page<Player> getAllByPage(Pageable pageable);
}
