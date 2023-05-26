package com.novuss.restfulservice.core.service.sports_class;

import com.novuss.restfulservice.core.port.in.sports_class.DeleteSportsClassByIdUseCase;
import com.novuss.restfulservice.core.port.out.sports_class.DeleteSportsClassByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteSportsClassByIdService implements DeleteSportsClassByIdUseCase {
    private final DeleteSportsClassByIdPort deleteSportsClassByIdPort;
    @Override
    public void delete(String id) {
        deleteSportsClassByIdPort.delete(id);
    }
}
