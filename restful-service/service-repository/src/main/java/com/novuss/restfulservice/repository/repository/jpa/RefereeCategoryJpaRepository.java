package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.RefereeCategoryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefereeCategoryJpaRepository extends JpaRepository<RefereeCategoryEntity, UUID> {
    Optional<RefereeCategoryEntity> findByTitle(String title);
    @EntityGraph(value = "RefereeCategoryEntity.referees", type = EntityGraph.EntityGraphType.LOAD)
    Optional<RefereeCategoryEntity> findById(UUID id);
}
