package com.novuss.restfulservice.core.service.sportsClass;

import com.novuss.restfulservice.core.port.in.sportsClass.SaveSportsClassUseCase;
import com.novuss.restfulservice.core.port.out.sportsClass.SaveSportsClassPort;
import com.novuss.restfulservice.domain.SportsClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveSportsClassService implements SaveSportsClassUseCase {
    private final SaveSportsClassPort saveSportsClassPort;
    @Override
    public SportsClass save(SportsClass sportsClass) {
        return saveSportsClassPort.save(sportsClass);
    }
}