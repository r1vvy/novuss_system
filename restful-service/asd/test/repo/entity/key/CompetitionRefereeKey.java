package com.novuss.restfulservice.repository.key;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class CompetitionRefereeKey implements Serializable {
    @ManyToOne
    @JoinColumn(name = "competition_id")
    CompetitionEntity competitionEntity;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    RefereeEntity refereeEntity;
}
