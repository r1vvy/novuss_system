package com.novuss.restfulservice.repository.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class CompetitionPlayerKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    PlayerEntity playerEntity;
    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    CompetitionEntity competitionEntity;
}
