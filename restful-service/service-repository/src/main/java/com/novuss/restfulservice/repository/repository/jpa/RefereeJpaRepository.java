package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.RefereeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefereeJpaRepository extends JpaRepository<RefereeEntity, UUID> {
    @Query("SELECT r FROM RefereeEntity r WHERE r.personEntity.firstName = :firstName AND r.personEntity.lastName = :lastName")
    Optional<RefereeEntity> findByPersonFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT r FROM RefereeEntity r WHERE r.personEntity.id = :personId")
    Optional<RefereeEntity> findByPersonId(@Param("personId") UUID personId);
}
