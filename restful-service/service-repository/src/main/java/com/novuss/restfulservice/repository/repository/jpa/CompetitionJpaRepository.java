package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.CompetitionEntity;
import com.novuss.restfulservice.repository.entity.CompetitionPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, UUID> {

    Optional<CompetitionEntity> findByTitle(String title);
}
