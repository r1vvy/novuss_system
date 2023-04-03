package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.CompetitionRefereeEntity;
import com.novuss.restfulservice.repository.key.CompetitionRefereeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRefereeJpaRepository extends JpaRepository<CompetitionRefereeEntity, CompetitionRefereeKey> {
}
