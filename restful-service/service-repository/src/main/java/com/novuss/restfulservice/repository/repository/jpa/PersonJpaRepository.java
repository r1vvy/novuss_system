package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, UUID>, JpaSpecificationExecutor<PersonEntity> {
    Optional<PersonEntity> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<PersonEntity> findByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
    Optional<PersonEntity> findByPhoneNumber(String phoneNumber);
}
