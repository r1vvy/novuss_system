package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.UpdateClubContactPersonByIdPort;
import com.novuss.restfulservice.domain.Club;
import com.novuss.restfulservice.repository.converter.ClubEntityToDomainConverter;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import com.novuss.restfulservice.repository.repository.jpa.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateClubContactPersonByIdAdapter implements UpdateClubContactPersonByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    @Override
    public Club updateClubContactPersonById(String clubId, String personId) {
        var clubUUID = UUID.fromString(clubId);
        var personUUID = UUID.fromString(personId);
        var clubEntity = clubJpaRepository.findById(clubUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Club with id = {} not found", clubId);
                            throw new EntityNotFoundException("Club not found");
                        }
                );
        var personEntity = personJpaRepository.findById(personUUID)
                .orElseThrow(
                        () -> {
                            log.warn("Person with id = {} not found", personId);
                            throw new EntityNotFoundException("Person not found");
                        }
                );
        clubEntity.setContactPersonEntity(personEntity);
        var updatedClubEntity = clubJpaRepository.save(clubEntity);

        return ClubEntityToDomainConverter.convert(updatedClubEntity);
    }
}
