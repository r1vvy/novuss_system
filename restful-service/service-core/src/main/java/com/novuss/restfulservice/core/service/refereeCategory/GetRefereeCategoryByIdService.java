package com.novuss.restfulservice.core.service.refereeCategory;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.referee.GetRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.in.refereeCategory.GetRefereeCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByIdPort;
import com.novuss.restfulservice.core.port.out.refereeCategory.FindRefereeCategoryByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetRefereeCategoryByIdService implements GetRefereeCategoryByIdUseCase {
    private final FindRefereeCategoryByIdPort findRefereeCategoryByIdPort;

    @Override
    public RefereeCategory getById(String id) {
        log.info("Searching for referee category with id = {}", id);
        return findRefereeCategoryByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Referee category not found"));
    }
}
