package com.novuss.restfulservice.repository.entity;

import com.novuss.restfulservice.repository.CompetitionPlayerCompositeKey;
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
@Table(name = "competition_players")
public class CompetitionPlayerEntity {
    @EmbeddedId
    private CompetitionPlayerCompositeKey id;
    @Column(name = "placement")
    private Integer placement;
    @Column(name = "rating_change")
    private Integer ratingChange;
    @Column(name = "rating_after")
    private Integer ratingAfter;

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
