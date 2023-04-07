package com.novuss.restfulservice.repository.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SportsClassJpaRepository extends JpaRepository<SportsClassEntity, UUID> {
}
