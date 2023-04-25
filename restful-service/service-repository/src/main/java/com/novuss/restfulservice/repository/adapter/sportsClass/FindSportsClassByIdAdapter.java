package com.novuss.restfulservice.repository.adapter.sportsClass;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.sportsClass.FindSportsClassByIdPort;
import com.novuss.restfulservice.domain.SportsClass;
import com.novuss.restfulservice.repository.converter.SportsClassEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FindSportsClassByIdAdapter implements FindSportsClassByIdPort {
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public SportsClass getById(String id) {

        return sportsClassJpaRepository.findById(UUID.fromString(id))
                .map(SportsClassEntityToDomainConverter::convert)
                .orElseThrow(() ->
                        new EntityNotFoundException("SportsClass not found with id = " + id)
                );
    }
}
