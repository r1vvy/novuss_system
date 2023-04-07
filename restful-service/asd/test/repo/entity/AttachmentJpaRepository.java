package com.novuss.restfulservice.repository.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentJpaRepository extends JpaRepository<AttachmentEntity, Long> {
}
