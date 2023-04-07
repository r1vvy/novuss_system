package com.novuss.restfulservice.repository.test;

import com.novuss.restfulservice.repository.key.CompetitionPlayerKey;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competitions")
public class CompetitionPlayerEntity {
    @EmbeddedId
    private CompetitionPlayerKey id;
    @Column(name = "placement")
    private Integer placement;
    @Column(name = "rating_change")
    private Integer ratingChange;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
