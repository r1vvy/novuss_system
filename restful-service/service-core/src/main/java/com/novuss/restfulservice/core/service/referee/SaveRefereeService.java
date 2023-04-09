package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.in.referee.SaveRefereeUseCase;
import com.novuss.restfulservice.core.port.out.person.FindPersonByFirstnameAndLastnameAndPhonenumberPort;
import com.novuss.restfulservice.core.port.out.referee.FindRefereeByPersonFirstnameAndLastnamePort;
import com.novuss.restfulservice.core.port.out.referee.SaveRefereePort;
import com.novuss.restfulservice.domain.Referee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaveRefereeService implements SaveRefereeUseCase {
    private final SaveRefereePort saveRefereePort;
    private final FindPersonByFirstnameAndLastnameAndPhonenumberPort findPersonByFirstnameAndLastnameAndPhonenumberPort;

    @Override
    public Referee save(Referee referee) {

        return saveRefereePort.save(referee);
    }
}
