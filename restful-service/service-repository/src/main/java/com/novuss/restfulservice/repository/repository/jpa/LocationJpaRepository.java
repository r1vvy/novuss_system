package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {
    Optional<LocationEntity> findByTitle(String title);

    @Query("SELECT COUNT(*) FROM LocationEntity l WHERE l.personEntity.id = :personId")
    int countLocationEntitiesByPersonEntityId(@Param("personId") UUID personId);
}
