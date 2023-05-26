package com.novuss.restfulservice.core.service.referee_category;

import com.novuss.restfulservice.core.port.in.referee_category.UpdateRefereeCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.referee_category.UpdateRefereeCategoryByIdPort;
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