package com.novuss.restfulservice.core.service.refereeCategory;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.refereeCategory.GetRefereeCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.referee.FindRefereesByRefereeCategoryIdPort;
import com.novuss.restfulservice.core.port.out.refereeCategory.FindRefereeCategoryByIdPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetRefereeCategoryByIdService implements GetRefereeCategoryByIdUseCase {
    private final FindRefereeCategoryByIdPort findRefereeCategoryByIdPort;
    private final FindRefereesByRefereeCategoryIdPort findRefereesByRefereeCategoryIdPort;

    @Override
    public RefereeCategory getById(String id) {
        log.info("Searching for referee category with id = {}", id);
        var refereeCategory = findRefereeCategoryByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Referee category not found"));
        var referees = findRefereesByRefereeCategoryIdPort.findByRefereeCategoryId(id);

        return refereeCategory.toBuilder()
                .referees(referees.map(HashSet::new)
                        .orElse(null)
                )
                .build();
    }
}
