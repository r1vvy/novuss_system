package com.novuss.restfulservice.domain.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSpecificationsBuilder {
    private final List<SpecSearchCriteria> params;
}
