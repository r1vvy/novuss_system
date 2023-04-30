package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.ClubEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClubJpaRepository extends JpaRepository<ClubEntity, UUID>, JpaSpecificationExecutor<ClubEntity> {
    Optional<ClubEntity> findClubEntityByTitle(String title);
    @Query("SELECT COUNT(*) FROM ClubEntity l WHERE l.contactPersonEntity.id = :personId")
    int countClubEntitiesByPersonEntityId(@Param("personId") UUID personId);
}
