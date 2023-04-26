package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.UpdateClubByIdPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubDomainToEntityConverter;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateClubByIdAdapter implements UpdateClubByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public Club updateById(Club club, String id) {
        var uuid = UUID.fromString(id);

        var oldEntity = clubJpaRepository.findById(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("Club not found with id = " + id)
                );
        var updatedEntity = ClubDomainToEntityConverter.convert(club);
        updatedEntity.setId(uuid);
        updatedEntity.setCreatedAt(oldEntity.getCreatedAt());

        var updatedClub = clubJpaRepository.save(updatedEntity);

        return ClubEntityToDomainConverter.convert(updatedClub);
    }
}
