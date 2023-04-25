package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SportsClassJpaRepository extends JpaRepository<SportsClassEntity, UUID> {
    Optional<SportsClassEntity> findByTitle(String title);
}
