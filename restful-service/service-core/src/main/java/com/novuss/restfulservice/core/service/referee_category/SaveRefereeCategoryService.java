package com.novuss.restfulservice.core.service.referee_category;

import com.novuss.restfulservice.core.port.in.referee_category.SaveRefereeCategoryUseCase;
import com.novuss.restfulservice.core.port.out.referee_category.SaveRefereeCategoryPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveRefereeCategoryService implements SaveRefereeCategoryUseCase {
    private final SaveRefereeCategoryPort saveRefereeCategoryPort;

    @Override
    public RefereeCategory save(RefereeCategory refereeCategory) {
        return saveRefereeCategoryPort.save(refereeCategory);
    }
}
