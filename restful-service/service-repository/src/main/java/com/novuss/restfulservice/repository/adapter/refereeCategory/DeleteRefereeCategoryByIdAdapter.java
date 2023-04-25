package com.novuss.restfulservice.repository.adapter.refereeCategory;

import com.novuss.restfulservice.core.port.out.refereeCategory.DeleteRefereeCategoryByIdPort;
import com.novuss.restfulservice.repository.repository.jpa.RefereeCategoryJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteRefereeCategoryByIdAdapter implements DeleteRefereeCategoryByIdPort {
    private final RefereeCategoryJpaRepository refereeCategoryJpaRepository;
    @Override
    public void deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        refereeCategoryJpaRepository.findById(uuid)
                .orElseThrow(() -> {
                    log.error("Referee category with id {} not found", id);
                    throw new EntityNotFoundException("Referee category with id " + id + " not found");
                });

        refereeCategoryJpaRepository.deleteById(uuid);
    }
}
