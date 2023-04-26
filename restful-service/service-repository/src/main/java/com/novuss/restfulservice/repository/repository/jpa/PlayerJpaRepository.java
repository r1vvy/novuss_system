package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, UUID> {

    @Query("SELECT p FROM PlayerEntity p WHERE " +
            "p.personEntity.firstName = :firstname " +
            "AND p.personEntity.lastName = :lastname " +
            "AND p.personEntity.phoneNumber = :phoneNumber")
    Optional<PlayerEntity> findByPersonEntityFirstnameAndLastnameAndPhonenumber(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("phoneNumber") String phoneNumber
    );
}
