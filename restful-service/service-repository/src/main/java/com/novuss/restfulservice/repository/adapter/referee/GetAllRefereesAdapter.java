package com.novuss.restfulservice.repository.adapter.referee;

import com.novuss.restfulservice.core.port.out.referee.GetAllRefereesPort;
import com.novuss.restfulservice.domain.Referee;
import com.novuss.restfulservice.repository.converter.RefereeEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.RefereeJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllRefereesAdapter implements GetAllRefereesPort {
    private final RefereeJpaRepository refereeJpaRepository;

    @Override
    public Page<Referee> getAllRefereesByPage(Pageable pageable) {
        var page = refereeJpaRepository.findAll(pageable);

        return page.map(RefereeEntityToDomainConverter::convert);
    }
}
