package com.novuss.restfulservice.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionRefereeCompositeKey implements Serializable {
    @Column(name = "referee_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    UUID refereeEntityId;
    @Column(name = "competition_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    UUID competitionEntityId;
}
