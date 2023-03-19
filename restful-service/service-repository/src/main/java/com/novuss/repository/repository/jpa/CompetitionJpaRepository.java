package com.novuss.repository.repository.jpa;

import com.novuss.repository.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, UUID> {
}
