package com.novuss.restfulservice.repository.test.repo;

import com.novuss.restfulservice.repository.key.CompetitionRefereeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRefereeJpaRepository extends JpaRepository<CompetitionRefereeEntity, CompetitionRefereeKey> {
}
