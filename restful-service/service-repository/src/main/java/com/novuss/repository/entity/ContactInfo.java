package com.novuss.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contacts")
public class ContactInfo {
    @Column(name = "id")
    UUID id;

    String phoneNumber;

    String email;

    LocalDate registeredAt;

    LocalDate updatedAt;
}
