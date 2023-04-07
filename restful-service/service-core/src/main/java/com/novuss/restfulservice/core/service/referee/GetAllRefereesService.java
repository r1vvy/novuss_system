package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.port.in.person.GetAllPeopleUseCase;
import com.novuss.restfulservice.core.port.in.referee.GetAllRefereesUseCase;
import com.novuss.restfulservice.core.port.out.referee.GetAllRefereesPort;
import com.novuss.restfulservice.domain.Referee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAllRefereesService implements GetAllRefereesUseCase {
    private final GetAllRefereesPort getAllRefereesPort;
    @Override
    public List<Referee> getAll() {
        return getAllRefereesPort.getAllReferees();
    }
}
