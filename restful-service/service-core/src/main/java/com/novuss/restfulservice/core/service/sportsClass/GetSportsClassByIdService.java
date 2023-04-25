package com.novuss.restfulservice.core.service.sportsClass;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.sportsClass.GetSportsClassByIdUseCase;
import com.novuss.restfulservice.core.port.out.sportsClass.FindSportsClassByIdPort;
import com.novuss.restfulservice.domain.SportsClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetSportsClassByIdService implements GetSportsClassByIdUseCase {
    private final FindSportsClassByIdPort findSportsClassByIdPort;
    @Override
    public SportsClass getById(String id) {
        return findSportsClassByIdPort.getById(id);
    }
}
