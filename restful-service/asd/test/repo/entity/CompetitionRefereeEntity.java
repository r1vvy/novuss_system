package com.novuss.restfulservice.repository.test;

import com.novuss.restfulservice.repository.key.CompetitionRefereeKey;
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
@Table(name = "competition_referees")
public class CompetitionRefereeEntity {
    @EmbeddedId
    private CompetitionRefereeKey id;
    @Column(name = "role")
    private String role;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
