package com.novuss.restfulservice.repository.entity;

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
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "competitions")
public class CompetitionEntity {

    @Id
    @Column(name = "id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "image_location")
    private String image;
    @Column(name = "registration_open_at")
    private LocalDateTime registrationOpenAt;
    @Column(name = "registration_close_at")
    private LocalDateTime registrationCloseAt;
    @Column(name = "start_at")
    private LocalDateTime startAt;
    @Column(name = "end_at")
    private LocalDateTime endAt;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "competitionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<AttachmentEntity> attachments = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private CategoryEntity categoryEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ContactInfoEntity contactInfoEntity;

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
