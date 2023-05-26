package com.novuss.restfulservice.core.port.out.competition;

public interface AddFileForCompetitionPort {
    void addFileById(String competitionId, String fileId);
}
