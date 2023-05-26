package com.novuss.restfulservice.core.service.referee_category;

import com.novuss.restfulservice.core.port.in.referee_category.GetAllRefereeCategoriesUseCase;
import com.novuss.restfulservice.core.port.out.referee_category.GetAllRefereeCategoriesPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllRefereeCategoriesService implements GetAllRefereeCategoriesUseCase {
    private final GetAllRefereeCategoriesPort getAllRefereeCategoriesPort;

    @Override
    public List<RefereeCategory> getAll() {
        log.info("Getting all referee categories");
        return getAllRefereeCategoriesPort.getAll();
    }
}
