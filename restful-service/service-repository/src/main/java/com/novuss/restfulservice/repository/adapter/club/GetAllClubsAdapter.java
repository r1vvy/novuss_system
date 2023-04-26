package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.port.out.club.GetAllClubsPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllClubsAdapter implements GetAllClubsPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public List<Club> getAllClubs() {

        return clubJpaRepository.findAll()
                .stream()
                .map(ClubEntityToDomainConverter::convert)
                .collect(Collectors.toList());
    }
}
