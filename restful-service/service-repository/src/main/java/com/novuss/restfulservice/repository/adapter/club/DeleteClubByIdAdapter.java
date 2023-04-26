package com.novuss.restfulservice.repository.adapter.club;

import com.novuss.restfulservice.core.port.out.club.DeleteClubByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.ClubJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteClubByIdAdapter implements DeleteClubByIdPort {
    private final ClubJpaRepository clubJpaRepository;
    @Override
    public void deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        clubJpaRepository.findById(uuid).ifPresentOrElse(
                clubEntity -> {
                    log.info("Club with id {} found", id);
                    clubJpaRepository.deleteById(uuid);
                },
                () -> {
                    log.info("Club with id {} not found", id);
                    throw new RuntimeException("Club with id " + id + " not found");
                }
        );
    }
}
