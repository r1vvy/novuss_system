package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.person.GetAllPeoplePort;
import com.novuss.restfulservice.core.port.out.referee.GetAllRefereesPort;
import com.novuss.restfulservice.domain.Person;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.MapStructMapper;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllRefereesAdapter implements GetAllRefereesPort {
    private final RefereeJpaRepository refereeJpaRepository;
    private final MapStructMapper mapStructMapper;

    @Override
    public List<Referee> getAllReferees() {
        return refereeJpaRepository.findAll()
                .stream()
                .map(mapStructMapper::refereeEntityToDomain)
                .collect(Collectors.toList());
    }
}
