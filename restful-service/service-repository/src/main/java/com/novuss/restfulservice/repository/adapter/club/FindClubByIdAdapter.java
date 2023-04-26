package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.FindClubByIdPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindClubByIdAdapter implements FindClubByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public Club findById(String id) {
        return clubJpaRepository.findById(UUID.fromString(id))
                .map(ClubEntityToDomainConverter::convert)
                .orElseThrow(() ->
                        new EntityNotFoundException("Club not found with id = " + id)
                );
    }
}
