package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.out.club.SaveClubPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SaveClubAdapter implements SaveClubPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public Club save(Club club) {
        clubJpaRepository.findClubEntityByTitle(club.title())
                .ifPresent(clubEntity -> {
            throw new EntityExistsException("Club already exists with title = " + club.title());
        });
        var clubEntity = ClubDomainToEntityConverter.convert(club);
        var updatedEntity = clubJpaRepository.save(clubEntity);

        return ClubEntityToDomainConverter.convert(updatedEntity);
    }
}
