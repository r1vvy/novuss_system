package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.port.out.club.GetAllClubsByPagePort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllClubsByPageAdapter implements GetAllClubsByPagePort {
    private final ClubJpaRepository clubJpaRepository;

    @Override
    public Page<Club> getAll(Pageable pageable) {
        var page = clubJpaRepository.findAll(pageable);

        return page.map(ClubEntityToDomainConverter::convert);
    }
}
