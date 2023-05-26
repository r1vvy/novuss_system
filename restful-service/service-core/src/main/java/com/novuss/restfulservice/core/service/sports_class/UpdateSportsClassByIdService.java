package com.novuss.restfulservice.core.service.sports_class;

import com.novuss.restfulservice.core.port.in.sports_class.UpdateSportsClassByIdUseCase;
import com.novuss.restfulservice.core.port.out.sports_class.UpdateSportsClassByIdPort;
import com.novuss.restfulservice.domain.SportsClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateSportsClassByIdService implements UpdateSportsClassByIdUseCase {
    private final UpdateSportsClassByIdPort updateSportsClassByIdPort;

    @Override
    public SportsClass update(SportsClass sportsClass, String id) {
        return updateSportsClassByIdPort.updateById(sportsClass, id);
    }
}
