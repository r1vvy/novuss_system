package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.SportsClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SportsClassJpaRepository extends JpaRepository<SportsClassEntity, UUID> {
}
