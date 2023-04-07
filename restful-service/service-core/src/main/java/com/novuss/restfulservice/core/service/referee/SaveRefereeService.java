package com.novuss.restfulservice.core.service.referee;

import com.novuss.restfulservice.core.exception.EntityExistsException;
import com.novuss.restfulservice.core.port.in.referee.SaveRefereeUseCase;
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
    private final FindRefereeByPersonFirstnameAndLastnamePort findRefereeByPersonFirstnameAndLastnamePort;
    private final SaveRefereePort saveRefereePort;

    @Override
    public Referee save(Referee referee) {
        var person = referee.person();
        var firstName = person.firstName();
        var lastName = person.lastName();

        findRefereeByPersonFirstnameAndLastnamePort.find(firstName, lastName)
                .ifPresent(p ->
                {
                    log.error("Referee with name {} and lastname {} already exists", firstName, lastName);
                    throw new EntityExistsException("Referee with name " + firstName + " and lastname " + lastName + " already exists");
                });

        return saveRefereePort.save(referee);
    }
}
