package com.novuss.restfulservice.repository.repository.jpa;

import com.novuss.restfulservice.repository.entity.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, UUID>, JpaSpecificationExecutor<PlayerEntity> {

    @Query("SELECT p FROM PlayerEntity p WHERE " +
            "p.personEntity.firstName = :firstname " +
            "AND p.personEntity.lastName = :lastname " +
            "AND p.personEntity.phoneNumber = :phoneNumber")
    Optional<PlayerEntity> findByPersonEntityFirstnameAndLastnameAndPhonenumber(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("phoneNumber") String phoneNumber
    );

    @Query("SELECT p FROM PlayerEntity p JOIN p.personEntity ps WHERE \n" +
            "    (:name IS NULL OR CONCAT(LOWER(ps.firstName), ' ', LOWER(ps.lastName)) = LOWER(:name))"
    )
    Page<PlayerEntity> findAllByPersonEntityFullName(
            @Param("name")
            String name,
            Pageable pageable
    );

    @Query("SELECT r FROM PlayerEntity r WHERE r.personEntity.id = :personId")
    Optional<PlayerEntity> findByPersonId(@Param("personId") UUID personId);
}
