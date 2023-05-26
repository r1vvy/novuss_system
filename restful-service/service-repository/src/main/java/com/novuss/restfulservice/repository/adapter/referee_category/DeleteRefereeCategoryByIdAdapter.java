package com.novuss.restfulservice.repository.adapter.referee_category;

import com.novuss.restfulservice.core.port.out.referee_category.DeleteRefereeCategoryByIdPort;
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
        UUID categoryId = UUID.fromString(id);
        refereeCategoryJpaRepository.findById(categoryId)
                .orElseThrow(() -> {
                    log.error("Referee category with id {} not found", id);
                    throw new EntityNotFoundException("Referee category with id " + id + " not found");
                });

        refereeCategoryJpaRepository.deleteById(categoryId);
    }
}
