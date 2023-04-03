package com.novuss.restfulservice.repository.entity;

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
@Table(name = "referees")
public class RefereeEntity {
    @Id
    @Column(name = "id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    @Column(name = "city")
    private String city;
    @Column(name = "category")
    private String category;
    @Column(name = "commission_number")
    private String commissionNumber;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity personEntity;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
