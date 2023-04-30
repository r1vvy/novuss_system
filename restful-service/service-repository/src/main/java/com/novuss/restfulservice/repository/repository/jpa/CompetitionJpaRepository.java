package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, UUID>, JpaSpecificationExecutor<CompetitionEntity> {

    Optional<CompetitionEntity> findByTitle(String title);
}
