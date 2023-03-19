package com.novuss.repository.repository.jpa;

import com.novuss.repository.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LocationJpaRepository extends JpaRepository<LocationEntity, UUID> {
}
