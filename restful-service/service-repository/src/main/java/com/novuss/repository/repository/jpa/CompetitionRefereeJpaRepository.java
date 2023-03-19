package com.novuss.repository.repository.jpa;

import com.novuss.repository.entity.CompetitionRefereeEntity;
import com.novuss.repository.key.CompetitionRefereeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRefereeJpaRepository extends JpaRepository<CompetitionRefereeEntity, CompetitionRefereeKey> {
}
