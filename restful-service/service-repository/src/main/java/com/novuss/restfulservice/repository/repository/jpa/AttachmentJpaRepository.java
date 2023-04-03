package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.AttachmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentJpaRepository extends JpaRepository<AttachmentEntity, Long> {
}
