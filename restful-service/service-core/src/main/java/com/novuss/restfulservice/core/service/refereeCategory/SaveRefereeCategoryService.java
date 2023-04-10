package com.novuss.restfulservice.core.service.refereeCategory;

import com.novuss.restfulservice.core.port.in.referee.SaveRefereeUseCase;
import com.novuss.restfulservice.core.port.in.refereeCategory.SaveRefereeCategoryUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnameAndPhonenumberPort;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.core.port.out.refereeCategory.SaveRefereeCategoryPort;
import com.novuss.restfulservice.domain.Referee;
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
