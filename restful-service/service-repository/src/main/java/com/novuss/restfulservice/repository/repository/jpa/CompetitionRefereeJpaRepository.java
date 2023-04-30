package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import com.novuss.restfulservice.repository.entity.CompetitionRefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionRefereeJpaRepository extends JpaRepository<CompetitionRefereeEntity, CompetitionRefereeCompositeKey> {
    @Query("SELECT c FROM CompetitionRefereeEntity c WHERE c.id.competitionEntityId = :competitionId")
    List<CompetitionRefereeEntity> findAllByCompetitionEntityId(@Param("competitionId") UUID competitionId);
}
