package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.domain.Location;
import com.novuss.restfulservice.repository.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {
    Optional<LocationEntity> findByTitle(String title);
}
