package com.novuss.restfulservice.repository.entity;

import com.novuss.restfulservice.repository.key.CompetitionPlayerKey;
import jakarta.persistence.*;
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
