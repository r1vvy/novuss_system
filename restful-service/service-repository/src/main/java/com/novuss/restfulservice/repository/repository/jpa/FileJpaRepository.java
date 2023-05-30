package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileJpaRepository extends JpaRepository<FileEntity, UUID> {

    List<FileEntity> findByCompetitionEntityId(UUID competitionId);
}
