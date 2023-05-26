package com.novuss.restfulservice.repository.adapter.referee_category;

import com.novuss.restfulservice.core.port.out.referee_category.GetAllRefereeCategoriesPort;
import com.novuss.restfulservice.domain.RefereeCategory;
import com.novuss.restfulservice.repository.converter.RefereeCategoryEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllRefereeCategoriesAdapter implements GetAllRefereeCategoriesPort {
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;

    @Override
    public List<RefereeCategory> getAll() {

        return refereeCategoryJpaRepository.findAll()
                .stream()
                .map(RefereeCategoryEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
