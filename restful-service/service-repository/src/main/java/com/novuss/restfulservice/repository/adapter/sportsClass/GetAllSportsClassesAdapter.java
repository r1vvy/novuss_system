package com.novuss.restfulservice.repository.adapter.sportsClass;

import com.novuss.restfulservice.core.port.out.sportsClass.GetAllSportsClassesPort;
import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.converter.SportsClassEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllSportsClassesAdapter implements GetAllSportsClassesPort {
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public List<SportsClass> getAll() {
        return sportsClassJpaRepository.findAll()
                .stream()
                .map(SportsClassEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
