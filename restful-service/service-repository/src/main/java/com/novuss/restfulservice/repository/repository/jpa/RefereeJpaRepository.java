package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RefereeJpaRepository extends JpaRepository<RefereeEntity, UUID> {
}
