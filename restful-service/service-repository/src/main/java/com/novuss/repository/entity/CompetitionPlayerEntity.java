package com.novuss.repository.entity;

import com.novuss.repository.key.CompetitionPlayerKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

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
