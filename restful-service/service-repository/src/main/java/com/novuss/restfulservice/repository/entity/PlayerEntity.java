package com.novuss.restfulservice.repository.entity;

import com.restfulservice.domain.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
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
    private UUID id;
    @Column(name = "image")
    private String image;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "gender", columnDefinition = "ENUM('MALE', 'FEMALE')")
    private Enum<Gender> genderEnum;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity personEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubEntity club;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licence_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LicenceEntity licenceEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sports_class_id", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private SportsClassEntity sportsClassEntity;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
