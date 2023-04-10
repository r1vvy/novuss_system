package com.novuss.restfulservice.core.service.refereeCategory;

import com.novuss.restfulservice.core.port.in.referee.UpdateRefereeByIdUseCase;
import com.novuss.restfulservice.core.port.in.refereeCategory.UpdateRefereeCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.referee.UpdateRefereeByIdPort;
import com.novuss.restfulservice.core.port.out.refereeCategory.UpdateRefereeCategoryByIdPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateRefereeCategoryByIdService implements UpdateRefereeCategoryByIdUseCase {
    private final UpdateRefereeCategoryByIdPort updateRefereeCategoryByIdPort;

    @Override
    public RefereeCategory updateById(String id, RefereeCategory refereeCategory) {
        return updateRefereeCategoryByIdPort.updateById(id, refereeCategory);
    }
}