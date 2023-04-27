package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.UpdateClubByIdPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.entity.ClubEntity;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import com.novuss.restfulservice.repository.entity.PersonEntity;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.LocationJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateClubByIdAdapter implements UpdateClubByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public Club updateById(Club club, String id) {
        var clubId = UUID.fromString(id);

        var oldClubEntity = clubJpaRepository.findById(clubId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Club not found with id = " + id)
                );

        var updatedEntity = ClubDomainToEntityConverter.convert(club);
        updatedEntity.setId(clubId);
        updatedEntity.setCreatedAt(oldClubEntity.getCreatedAt());
        updatedEntity.setContactPersonEntity(oldClubEntity.getContactPersonEntity());
        updatedEntity.setLocationEntity(oldClubEntity.getLocationEntity());

        var updatedClub = clubJpaRepository.save(updatedEntity);

        return ClubEntityToDomainConverter.convert(updatedClub);
    }
}
