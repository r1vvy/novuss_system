package com.novuss.restfulservice.core.service.sports_class;

import com.novuss.restfulservice.core.port.in.sports_class.GetSportsClassByIdUseCase;
import com.novuss.restfulservice.core.port.out.sports_class.FindSportsClassByIdPort;
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
