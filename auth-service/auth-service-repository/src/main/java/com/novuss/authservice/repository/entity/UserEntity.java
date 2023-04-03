package com.novuss.authservice.repository.entity;

import com.novuss.authservice.domain.UserRole;
import com.novuss.authservice.repository.converter.UserRoleConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Set;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "roles", columnDefinition = "SET('ADMIN', 'USER', 'EVENT_MANAGER')")
    @Convert(converter = UserRoleConverter.class)
    Set<UserRole> roles;
    @Column(name = "created_at", updatable = false)
    ZonedDateTime createdAt;
    @Column(name = "updated_at")
    ZonedDateTime updatedAt;

    @PrePersist
    private void prePersist() {
        createdAt = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
    }
}
