package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.CompetitionCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionCategoryJpaRepository extends JpaRepository<CompetitionCategoryEntity, UUID> {
    Optional<CompetitionCategoryEntity> findByTitle(String title);
}
