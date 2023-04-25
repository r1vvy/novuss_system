package com.novuss.restfulservice.core.service.sportsClass;

import com.novuss.restfulservice.core.port.in.sportsClass.GetAllSportsClassesUseCase;
import com.novuss.restfulservice.core.port.out.sportsClass.GetAllSportsClassesPort;
import com.novuss.restfulservice.domain.SportsClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllSportsClassesService implements GetAllSportsClassesUseCase {
    private final GetAllSportsClassesPort getAllSportsClassesPort;
    @Override
    public List<SportsClass> getAll() {
        return getAllSportsClassesPort.getAll();
    }
}
