package com.novuss.repository.repository.jpa;

import com.novuss.repository.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, UUID> {
}
