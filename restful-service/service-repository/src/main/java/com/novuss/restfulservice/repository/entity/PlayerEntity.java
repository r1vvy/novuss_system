package com.novuss.restfulservice.repository.entity;

import com.novuss.restfulservice.repository.converter.GenderConverter;
import com.novuss.restfulservice.domain.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @Column(name = "id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "image")
    private String image;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')")
    @Convert(converter = GenderConverter.class)
    private Gender gender;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity personEntity;
    @Column(name = "created_at", columnDefinition = "DATETIME",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant createdAt;
    @Column(name = "updated_at", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
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
