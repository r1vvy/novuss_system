package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.out.club.DeleteClubByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DeleteClubByIdAdapter implements DeleteClubByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public void deleteById(String id) {
        var clubId = UUID.fromString(id);
        clubJpaRepository.findById(clubId)
                .orElseThrow(
                        () -> {
                            log.error("Club with id {} not found", id);
                            return new EntityNotFoundException("Club with id " + id + " not found");
                        }
                );

        clubJpaRepository.deleteById(clubId);
        log.info("Club with id {} deleted", id);
    }
}
