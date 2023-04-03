package com.novuss.restfulservice.repository.entity;

import com.restfulservice.domain.UserRole;
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
@Table(name = "users")
public class UserEntity {
    @Id
    @Column(name = "id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role", columnDefinition = "enum('ADMIN', 'USER', 'EVENT_MANAGER')")
    private UserRole role;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
