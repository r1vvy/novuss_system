package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.entity.CompetitionPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CompetitionPlayerJpaRepository extends JpaRepository<CompetitionPlayerEntity, CompetitionPlayerCompositeKey> {

    @Query("SELECT c FROM CompetitionPlayerEntity c WHERE c.id.competitionEntityId = :competitionId")
    List<CompetitionPlayerEntity> findAllByCompetitionId(@Param("competitionId") UUID competitionId);
}
