package com.novuss.restfulservice.repository.entity;

import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
import com.novuss.restfulservice.repository.CompetitionRefereeCompositeKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competition_referees")
public class CompetitionRefereeEntity {
    @EmbeddedId
    private CompetitionRefereeCompositeKey id;

    @Column(name = "role")
    private String role;

    @Column(name = "created_at", columnDefinition = "DATETIME",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    private Instant createdAt;
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    @Basic(fetch = FetchType.EAGER)
    private Instant updatedAt;

    @PrePersist
    private void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = Instant.now();
    }
}
