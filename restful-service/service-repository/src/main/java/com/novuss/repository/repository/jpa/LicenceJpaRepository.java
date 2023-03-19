package com.novuss.repository.repository.jpa;

import com.novuss.repository.entity.LicenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LicenceJpaRepository extends JpaRepository<LicenceEntity, UUID> {
}
