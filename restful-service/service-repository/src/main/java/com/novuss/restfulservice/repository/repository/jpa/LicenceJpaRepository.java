package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.LicenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LicenceJpaRepository extends JpaRepository<LicenceEntity, UUID> {
    Optional<LicenceEntity> findByTitle(String title);
}
