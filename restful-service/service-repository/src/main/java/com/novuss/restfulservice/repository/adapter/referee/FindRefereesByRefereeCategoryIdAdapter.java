package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.referee.FindRefereesByRefereeCategoryIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FindRefereesByRefereeCategoryIdAdapter implements FindRefereesByRefereeCategoryIdPort {
    private final RefereeJpaRepository refereeJpaRepository;
    @Override
    public List<Referee> findByRefereeCategoryId(String refereeCategoryId) {
        log.info("Searching for referees with refereeCategoryId = {}", refereeCategoryId);
        var refereeEntities = refereeJpaRepository.findAllByCategoryId(UUID.fromString(refereeCategoryId))
                .orElseThrow(() -> {
                    log.warn("Referees with refereeCategoryId {} not found", refereeCategoryId);
                    throw new EntityNotFoundException("Referees with refereeCategoryId " + refereeCategoryId + " not found");
                });

        return refereeEntities.stream()
                .map(RefereeEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
