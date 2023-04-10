package com.novuss.restfulservice.core.service.refereeCategory;

import com.novuss.restfulservice.core.exception.EntityNotFoundException;
import com.novuss.restfulservice.core.port.in.refereeCategory.DeleteRefereeCategoryByIdUseCase;
import com.novuss.restfulservice.core.port.out.refereeCategory.DeleteRefereeCategoryByIdPort;
import com.novuss.restfulservice.core.port.out.refereeCategory.FindRefereeCategoryByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteRefereeCategoryByIdService implements DeleteRefereeCategoryByIdUseCase {
    private final DeleteRefereeCategoryByIdPort deleteRefereeCategoryByIdPort;
    private final FindRefereeCategoryByIdPort findRefereeCategoryByIdPort;

    public void deleteById(String id) {
        log.info("Trying to delete referee with id = {}", id);
        findRefereeCategoryByIdPort.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Referee not found"));

        deleteRefereeCategoryByIdPort.deleteById(id);
    }
}
