package com.novuss.restfulservice.repository.adapter.sportsClass;

import com.novuss.restfulservice.core.port.out.sports_class.DeleteSportsClassByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.SportsClassJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteSportsClassByIdAdapter implements DeleteSportsClassByIdPort {
    private final SportsClassJpaRepository sportsClassJpaRepository;
    @Override
    public void delete(String id) {
        UUID uuid = UUID.fromString(id);

        sportsClassJpaRepository.findById(uuid).ifPresentOrElse(
                sportsClassEntity -> {
                    log.info("SportsClass with id {} found", id);
                    sportsClassJpaRepository.deleteById(uuid);
                },
                () -> {
                    log.info("SportsClass with id {} not found", id);
                    throw new EntityNotFoundException("SportsClass with id " + id + " not found");
                }
        );
    }
}
